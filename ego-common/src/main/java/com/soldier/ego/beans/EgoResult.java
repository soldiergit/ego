package com.soldier.ego.beans;

import java.io.Serializable;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-24 上午8:00
 * @Describe: 封装客户端发送的商品上架、下架、删除请求后，需要响应的数据模型
 **/
public class EgoResult implements Serializable {

    //响应状态
    private Integer status;
    //响应数据
    private Object data;
    //响应消息
    private String msg;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public EgoResult(Integer status, Object data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public EgoResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public EgoResult(Object data) {
        this.data = data;
        this.status = 200;
        this.msg = "ok";
    }

    public EgoResult() {
        super();
    }

    /**
     * 静态方法，返回rgoResult对象
     */
    public static EgoResult ok() {
        return new EgoResult(null);
    }
}
