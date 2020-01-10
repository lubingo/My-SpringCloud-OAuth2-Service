package com.bingo.auth.core.eum;

import lombok.Data;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/10 13:34
 * @since
 */

public enum ResultCode {

    SUCCESS(0000,"成功"),
    COMMON_FAIL(9999, "失败"),

    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),

    NO_PERMISSION(3001, "没有权限");



    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据code获取message
     * @param code
     * @return message or null
     */
    public static String getMessageByCode(int code) {
        for (ResultCode ele : values()) {
            if (ele.getCode() == code) {
                return ele.getMessage();
            }
        }
        return getMessageByCode(9999);
    }
}
