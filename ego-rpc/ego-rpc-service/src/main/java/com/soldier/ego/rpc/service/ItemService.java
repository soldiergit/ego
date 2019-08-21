package com.soldier.ego.rpc.service;

import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.pojo.TbItem;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-21 上午11:40
 * @Describe:
 **/
public interface ItemService {

    /**
     * 实现商品信息的分页查询
     */
    public PageResult<TbItem> selectItemList(Integer page, Integer rows);
}
