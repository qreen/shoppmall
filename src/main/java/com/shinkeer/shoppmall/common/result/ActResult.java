package com.shinkeer.shoppmall.common.result;

import com.alibaba.fastjson.JSON;

/**
 * action 返回对象
 *
 * @author lgq
 * @date 2018/4/15
 **/
public class ActResult implements Result {
    private int code = 0;
    private String msg;
    private Object data;

    public ActResult() {
    }

    public ActResult(String msg) {
        this.msg = msg;
    }

    public ActResult(Object data) {
        this.data = data;
    }

    public ActResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ActResult(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public ActResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public static ActResult initialize(Object data) {
        return new ActResult(null, data);
    }

}
