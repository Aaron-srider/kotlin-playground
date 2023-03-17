package fit.wenchao.kotlinplayground.exception;

public class TOTPEmptyKeyException  extends  RuntimeException{
    public TOTPEmptyKeyException(String message) {
        super(message);
    }
}
