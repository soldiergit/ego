package com.soldier.ego.search.entity;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-23上午9:01
 * @Describe:封装solr索引库的查询结果
 **/
public class Item {

    //  导入solr的的标签
    @Field("id")
    private String id;
    @Field("item_title")
    private String item_title;
    @Field("item_sell_point")
    private String item_sell_point;
    @Field("item_price")
    private Long item_price;
    @Field("item_image")
    private String item_image;
    @Field("category_name")
    private String category_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public String getItem_sell_point() {
        return item_sell_point;
    }

    public void setItem_sell_point(String item_sell_point) {
        this.item_sell_point = item_sell_point;
    }

    public Long getItem_price() {
        return item_price;
    }

    public void setItem_price(Long item_price) {
        this.item_price = item_price;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    /**
     * 因为有多个图片，把当前图片地址切割了，返回出去
     */
    public String[] getImages() {
        String[] images = item_image.split(",");
        return images;
    }
}
