package com.soldier.ego.beans;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-4下午4:07
 * @Describe:封装tree控件需要的数据模型--用于商品类目
 **/
public class TreeNode {

    //节点id
    private Long id;
    //类目节点的名字
    private String text;
    //类目节点的状态--是否是父类目
    private String state;  //因为tree控件解析的是state，所以必须是state，不能是status

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
