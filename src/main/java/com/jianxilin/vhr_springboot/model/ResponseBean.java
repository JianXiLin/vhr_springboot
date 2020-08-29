package com.jianxilin.vhr_springboot.model;

public class ResponseBean {
    private Integer status;
    private String msg;
    private Object obj;

    public static ResponseBean success(String msg){
        return new ResponseBean(200,msg,null);
    }

    public static ResponseBean success(String msg,Object obj){
        return new ResponseBean(200,msg,obj);
    }
    public static ResponseBean fail(String msg){
        return new ResponseBean(500,msg,null);
    }

    public static ResponseBean fail(String msg,Object obj){
        return new ResponseBean(500,msg,obj);
    }

    public ResponseBean() {
    }

    public ResponseBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
