package com.example.marketsystem.exception;

/**
 * 捕获异常
 */
public class XDException extends RuntimeException{
    /**
     * 异常标记位
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;

    public XDException(){}
    public XDException(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
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
