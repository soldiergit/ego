package com.soldier.ego.search.service;

import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.search.entity.SearchResult;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-23上午9:19
 * @Describe:完成商品基本信息的检索
 **/
public interface SearchItemService {

    /**
     * 进行商品基本信息关键字查询查询，分页查询
     * @param item_keywords 关键字
     * @param page          当前页数
     */
    public SearchResult loadItemService(String item_keywords, Integer page);

    /**
     * 根据id获取商品信息
     * @param id    商品id
     */
    public TbItem loadItemService(Long id);
}
