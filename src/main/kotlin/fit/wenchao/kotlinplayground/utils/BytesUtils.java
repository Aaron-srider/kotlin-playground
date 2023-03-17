package fit.wenchao.kotlinplayground.utils;

public class BytesUtils {
    // get low 8 bytes of a long
    public static byte[] getLow8Bytes(long time) {
        byte[] challenge = new byte[8];
        for(int i  = 8; i-- > 0 ; time>>=8) {
            challenge[i] = (byte)time;
        }
        return challenge;
    }
}
