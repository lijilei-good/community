package vip.lijilei.community.exception;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/18
 */
public class CustomizeException extends RuntimeException{
    private String message;
    public CustomizeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
