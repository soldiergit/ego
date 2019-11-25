package com.soldier.ego.search.service;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-23上午9:19
 * @Describe:完成商品描述信息的检索
 **/
public interface SearchItemDescService {

    /**
     * 根据id获取商品描述信息的html代码，数据库存的是html
     * @param itemId    商品id
     */
    public String loadItemDescService(Long itemId);
}
