package com.soldier.ego.search.entity;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-23上午9:15
 * @Describe:封装响应到前台的数据模型
 **/
public class SearchResult {

    private List<Item> itemList;    //查询到的商品集合
    private Long totalPages;        //最大页数
    private Long total;             //查询到的商品总数

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
