package com.soldier.ego.manager.service;

import com.soldier.ego.beans.EgoResult;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-9-9 下午4:30
 * @Describe: 商品描述
 **/
public interface ManagerItemDescService {

    /**
     * 获得需要回显的商品描述信息
     * @param itemId
     * @return
     */
    public EgoResult getItemDesc(Long itemId);
}
