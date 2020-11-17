package com.example.marketsystem.utils;

/**
 * 返回类型工具类
 */
public class JosnData {
    /**
     * 状态码 0处理过   1 处理中 -1 失败
     */
    private Integer code;
    /**
     * 业务数据
     */
    private Object data;
    /**
     * 信息提示
     */
    private String msg;

    /**
     * 空构造函数
     */
    public JosnData(){}

    /**
     * 带参
     * @param code
     * @param data
     * @param msg
     */
    public JosnData(Integer code, Object data, String msg){
        this.code=code;
        this.data=data;
        this.msg=msg;
    }

    /**
     * 成功不返回数据
     * @return
     */
    public static JosnData buildSuccess(){
        return new JosnData(0,null,null);
    }

    /**
     * 成功返回信息
     * @param data
     * @return
     */
    public static JosnData buildSuccess(Object data){
        return new JosnData(0,data,null);
    }

    /**
     * 失败 固定码
     * @param msg
     * @return
     */
    public static JosnData buildError(String msg){
        return new JosnData(-1,null,msg);
    }

    /**
     * 失败 固定码 信息
     * @param code
     * @param msg
     * @return
     */
    public static JosnData buildError(Integer code,String msg){
        return new JosnData(code,null,msg);
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
