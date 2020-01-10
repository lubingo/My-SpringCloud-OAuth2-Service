package com.bingo.auth.core.entity.response;

import com.bingo.auth.core.eum.ResultCode;

/**
 * @author lubing
 * @describe 一句话描述
 * @Date 2020/1/10 13:49
 * @since
 */
public class ResponseResultUtil {

    public static <T>  ResponseJsonResult<T>  success(){
        return  new ResponseJsonResult(ResultCode.SUCCESS.getCode());
    }
    public static  <T> ResponseJsonResult<T> success(T data){
        return  new ResponseJsonResult(ResultCode.SUCCESS.getCode(),data);
    }

    public static  <T> ResponseJsonResult<T> fail(int code){
        return  new ResponseJsonResult(code);
    }
}
