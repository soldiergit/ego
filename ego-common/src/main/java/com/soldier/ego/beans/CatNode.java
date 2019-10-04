package com.soldier.ego.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-10-4下午6:37
 * @Describe:网站门户首页商品类目节点
 **/
public class CatNode {

    //@JsonProperty 指定将 java 对象转化为 json 格式的时候，对应的 key
    @JsonProperty(value="u")
    private String url;
    @JsonProperty(value="n")
    private String name;
    @JsonProperty(value="i")
    private List<?>list;
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<?> getList() {
        return list;
    }
    public void setList(List<?> list) {
        this.list = list;
    }
}
