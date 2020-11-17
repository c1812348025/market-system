package com.example.marketsystem.exception;
import java.util.concurrent.ExecutionException;


/**
 * PubGetException
 *
 * @author chuyx
 * @date 2020/11/3 15:29
 */
public class PubGetException extends RuntimeException{
    /**
     * 异常标记位
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;

    public PubGetException(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public PubGetException(){}
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
