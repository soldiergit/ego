package com.soldier.ego.beans;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create19-9-5下午6:43
 * @Describe:封装上传图片之后的返回结果
 **/
public class PictureResult {

    //图片上传的状态 1失败 0成功
    private Integer error;
    //图片上传后在服务器的路径
    private String url;
    //响应到客户端的提示消息
    private String message;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
