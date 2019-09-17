package com.soldier.ego.manager.service;

import com.soldier.ego.beans.EgoResult;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-17下午5:20
 * @Describe:商品规格参数信息接口
 **/
public interface ManagerItemParamItemService {

    /**
     * 根据商品id，查询商品对应的规格参数信息
     * @param itemId    商品id
     */
    public EgoResult loadItemParamItemList(Long itemId);
}
