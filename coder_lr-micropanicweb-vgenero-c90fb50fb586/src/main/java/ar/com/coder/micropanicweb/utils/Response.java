package ar.com.coder.micropanicweb.utils;

import java.util.List;

public class Response {

    private String status;
    private String msg;
    private Object data;
    
    
    public Response() {

    }

    public Response(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Response(String status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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