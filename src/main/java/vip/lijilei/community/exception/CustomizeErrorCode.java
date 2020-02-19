package vip.lijilei.community.exception;

/**
 * @author 李吉磊
 * @Company http://www.lijilei.vip
 * @Date: Create in 2020/2/19
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    QUESTION_NOT_FOUND(2001,"你的问题不在了，要不要换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002,"未选择任何一个问题进行回复")
    ;
    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message){
        this.message = message;
        this.code = code;
    }

}
