package com.soldier.ego.manager.service.impl;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.manager.service.ManagerItemDescService;
import com.soldier.ego.rpc.pojo.TbItemDesc;
import com.soldier.ego.rpc.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-9下午4:32
 * @Describe:商品描述
 **/
@Service
public class ManagerItemDescServiceImpl implements ManagerItemDescService {

    //注入的是远程服务的代理对象
    @Autowired
    private ItemDescService itemDescServiceProxy;

    @Override
    public EgoResult getItemDesc(Long itemId) {

        //调用远程服务
        TbItemDesc itemDesc = itemDescServiceProxy.getItemDescService(itemId);

        if (itemDesc != null) {
            return new EgoResult(itemDesc);
        }

        return new EgoResult(null);
    }
}
