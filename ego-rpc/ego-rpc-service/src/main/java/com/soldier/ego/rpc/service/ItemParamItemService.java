package com.soldier.ego.rpc.service;

import com.soldier.ego.rpc.pojo.TbItemParamItem;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-17下午5:04
 * @Describe:商品规格参数信息接口
 **/
public interface ItemParamItemService {

    /**
     * 根据商品id，查询商品对应的规格参数信息
     * @param itemId    商品id
     */
    public TbItemParamItem loadItemParamItemListService(Long itemId);
}
