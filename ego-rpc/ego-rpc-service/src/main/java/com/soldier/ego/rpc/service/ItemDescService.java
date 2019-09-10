package com.soldier.ego.rpc.service;

import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.rpc.pojo.TbItemDesc;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-9下午4:23
 * @Describe:商品描述信息接口
 **/
public interface ItemDescService {

    /**
     * 通过商品id获得商品描述信息
     * @param itemId
     * @return
     */
    public TbItemDesc getItemDescService(Long itemId);
}
