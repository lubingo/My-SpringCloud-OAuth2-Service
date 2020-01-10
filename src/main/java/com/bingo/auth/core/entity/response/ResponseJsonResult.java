package com.bingo.auth.core.entity.response;

import com.bingo.auth.core.eum.ResultCode;

import java.io.Serializable;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/10 13:44
 * @since
 */
public final class   ResponseJsonResult<T>   implements Serializable {

    private  int code ;
    private  String message ;
    private  T  data  ;


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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseJsonResult() {
    }

    public ResponseJsonResult(int  code) {
        this.code = code ;
        this.message = ResultCode.getMessageByCode(code);
        this.data = null;
    }

    public ResponseJsonResult(int  code ,T data) {
        this.code = code ;
        this.message = ResultCode.getMessageByCode(code);
        this.data = data;
    }

}
