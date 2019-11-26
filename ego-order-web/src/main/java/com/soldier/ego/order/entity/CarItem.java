package com.soldier.ego.order.entity;

import com.soldier.ego.rpc.pojo.TbItem;

import java.io.Serializable;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25下午1:13
 * @Describe:购物车类
 **/
public class CarItem implements Serializable {

    private TbItem item;    //购买的商品对象
    private Integer num;    //购买商品的数量

    public TbItem getItem() {
        return item;
    }

    public void setItem(TbItem item) {
        this.item = item;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
