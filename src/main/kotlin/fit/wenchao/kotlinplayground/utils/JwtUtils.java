package fit.wenchao.kotlinplayground.utils;

import fit.wenchao.kotlinplayground.config.properties.JWTProperties;
import fit.wenchao.kotlinplayground.exception.BackendException;
import fit.wenchao.kotlinplayground.exception.RespCode;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static fit.wenchao.kotlinplayground.config.properties.JWTExpirationTimeUnits.*;


/**
 * 该类主要调用JWT的API，用于创建token，验证token时效，解析token内容等。
 */
@Slf4j
@Component
public class JwtUtils implements InitializingBean {

    /**
     * token的过期时间, ms
     */
    public static long tokenExpiration;

    /**
     * 生成token所需的密钥
     */
    public static String tokenSignKey;

    /**
     * JWT的subject
     */
    public static String jwtSubject;

    private static String TOKEN_PAYLOAD_ID_ATTR = "id";

    /**
     * 为了让jwt的payload部分看起来不太一样，添加随机参数
     */
    private static final String TOKEN_PAYLOAD_RANDOM_FACTOR = "random-factor";

    public static String getIdFromToken(String token) {
        return JwtUtils.getClaimFromJWT(token, TOKEN_PAYLOAD_ID_ATTR);
    }

    public static String genToken(String id) {
        Map<String, Object> claim = new HashMap<>();
        claim.put(TOKEN_PAYLOAD_ID_ATTR, id);
        claim.put(TOKEN_PAYLOAD_RANDOM_FACTOR, UUID.randomUUID().toString());
        return JwtUtils.createToken(claim);
    }

    public static String genToken(String id, long expireDuration) {
        Map<String, Object> claim = new HashMap<>();
        claim.put(TOKEN_PAYLOAD_ID_ATTR, id);
        claim.put(TOKEN_PAYLOAD_RANDOM_FACTOR, UUID.randomUUID().toString());
        return JwtUtils.createToken(claim, System.currentTimeMillis() + expireDuration);
    }

    @Autowired
    JWTProperties jwtProperties;

    /**
     * 生成JWT
     *
     * @param claimMap claim Map
     * @param expireAt 指定JWT的失效时间
     * @return JWT
     */
    public static String createToken(Map<String, Object> claimMap, long expireAt) {
        Date expireAtDate = new Date(expireAt);
        return createToken(claimMap, expireAtDate);
    }

    /**
     * 生成JWT，失效时间在配置文件中配置
     *
     * @param claimMap claim Map
     * @return JWT
     */
    public static String createToken(Map<String, Object> claimMap) {
        Date expireAtDate = generateExpirationDate();
        return createToken(claimMap, expireAtDate);
    }


    private static Jws<Claims> verifySignature(String token) {
        return Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
    }

    /**
     * 使用tokenSignKey验证JWT的签名，同时检测JWT是否过期
     *
     * @param token 目标JWT
     * @return 如果签名验证通过且JWT没有过期，返回JWT的claims，否则报错
     */
    public static Jws<Claims> verifyJWT(String token) {
        return JwtUtils.verifySignature(token);
    }


    /**
     * 根据配置文件信息生成token的默认过期时间
     */
    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + tokenExpiration);
    }

    private static String createToken(Map<String, Object> claimMap, Date expireAtDate) {
        return Jwts.builder()
                   .setSubject(jwtSubject)
                   .setClaims(claimMap)
                   .setExpiration(expireAtDate)
                   .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                   .compressWith(CompressionCodecs.GZIP)
                   .compact();
    }

    /**
     * 从token中获取指定的claim
     *
     * @param <T>       待获取的claim的类型
     * @param token     指定的token
     * @param claimName 待获取的claim的名字
     * @return 待获取的claim
     */
    public static <T> T getClaimFromJWT(String token, String claimName) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Jws<Claims> claimsJws = verifySignature(token);
        Claims claims = claimsJws.getBody();
        T claim = (T) claims.get(claimName);
        return claim;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        jwtSubject = jwtProperties.getJwtSubject();
        String expirationUnit = jwtProperties.getExpirationUnit();
        long tokenExpirationValue = jwtProperties.getTokenExpiration();
        switch (expirationUnit) {
            case SECOND:
                tokenExpiration = tokenExpirationValue * 1000;
                break;
            case MINUTE:
                tokenExpiration = tokenExpirationValue * 1000 * 60;
                break;
            case HOUR:
                tokenExpiration = tokenExpirationValue * 1000 * 60 * 60;
                break;
            case DAY:
                tokenExpiration = tokenExpirationValue * 1000 * 60 * 60 * 24;
                break;
            case WEEK:
                tokenExpiration = tokenExpirationValue * 1000 * 60 * 60 * 24 * 7;
                break;
            default:
                tokenExpiration = tokenExpirationValue;
        }

        tokenSignKey = jwtProperties.getTokenSignKey();
    }


    /**
     * 时间常量，基础单位是ms
     */
    public enum Time {
        MILLI(1L),
        SECOND((long) 1000 * MILLI.value),
        MINUTE((long) 60 * SECOND.value),
        HOUR((long) 60 * MINUTE.value),
        DAY((long) 24 * HOUR.value),
        YEAR((long) 365 * DAY.value),
        WEEK((long) 7 * DAY.value);

        private final Long value;

        Time(Long value) {
            this.value = value;
        }

        public Long getValue() {
            return this.value;
        }
    }

    /**
     * 捕获jwt解析时的异常，转换成BackendException
     *
     * @param token 目标JWT
     * @return 如果签名验证通过且JWT没有过期，返回JWT的claims，否则报错
     */
    public static Jws<Claims> verifyJwtThrowEx(String token) {
        try {
            return JwtUtils.verifyJWT(token);
        } catch (SignatureException ex) {
            //签名认证错误
            throw new BackendException(null, RespCode.TOKEN_JWT_SIGN_ERROR);
        } catch (ExpiredJwtException ex) {
            //token失效
            throw new BackendException(null, RespCode.TOKEN_JWT_EXPIRED);
        } catch (UnsupportedJwtException ex) {
            //jwt 格式与application规定的不符
            throw new BackendException(null, RespCode.TOKEN_JWT_NOT_MATCH_REQUIREMENT);
        } catch (MalformedJwtException ex) {
            //token格式错误
            throw new BackendException(null, RespCode.TOKEN_JWT_FORMAT_ERROR);
        } catch (CompressionException ex) {
            //token体解压错误
            throw new BackendException(null, RespCode.TOKEN_JWT_BODY_ERROR);
        } catch (Exception ex) {
            //其他token错误
            throw new BackendException(null, RespCode.TOKEN_JWT_ERROR);
        }
    }

}
