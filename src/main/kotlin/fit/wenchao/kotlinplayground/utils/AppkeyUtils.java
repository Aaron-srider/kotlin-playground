package fit.wenchao.kotlinplayground.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

public class AppkeyUtils {

    private final static String[] CHAR_SET = {"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    private final static String APP_SECRET_KEY = "kshaifihuafhsd";

    /**
     * 利用32位UUID，每4个为一组对62取模，转换为62个可打印字符之一，共产生8位字符。
     */
    public static String getAppId() {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(CHAR_SET[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static String getAppSecret(String appId) {
        try {
            String[] sourceArray = {appId, APP_SECRET_KEY};

            StringBuilder sb = new StringBuilder();
            Arrays.sort(sourceArray);
            for (int i = 0; i < sourceArray.length; i++) {
                sb.append(sourceArray[i]);
            }
            String sourceString = sb.toString();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(sourceString.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                // & 0xFF 保证结果是正数（0xFF 是整形，digest[i]先转成整形，必为正数）
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static String getAccessToken() {
        return UUID.randomUUID().toString();
    }

    public static String getAccessToken(long id, long expireFor) {
        return JwtUtils.genToken(String.valueOf(id), expireFor);
    }
}