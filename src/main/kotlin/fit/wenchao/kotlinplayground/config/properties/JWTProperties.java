package fit.wenchao.kotlinplayground.config.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static fit.wenchao.kotlinplayground.config.properties.JWTExpirationTimeUnits.MINUTE;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@ConfigurationProperties(prefix = "jwt")
@Component
public class JWTProperties {

    /**
     * token的过期时间, milli
     */
    public long tokenExpiration = 30;


    public String expirationUnit = MINUTE;

    /**
     * 生成token所需的密钥
     */
    public String tokenSignKey = "111111";

    /**
     * JWT的subject
     */
    public String jwtSubject = "TOTP-ADMIN-USER";

}
