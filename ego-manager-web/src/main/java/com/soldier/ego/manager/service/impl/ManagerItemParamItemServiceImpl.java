package com.soldier.ego.manager.service.impl;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.manager.service.ManagerItemParamItemService;
import com.soldier.ego.rpc.pojo.TbItemParamItem;
import com.soldier.ego.rpc.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-17下午5:22
 * @Describe:商品规格参数信息接口
 **/
@Service
public class ManagerItemParamItemServiceImpl implements ManagerItemParamItemService {

    //注入的是远程服务的代理对象
    @Autowired
    private ItemParamItemService itemParamItemServiceProxy;

    @Override
    public EgoResult loadItemParamItemList(Long itemId) {
        EgoResult result = null;
        try {
            //调用远程服务代理对象
            TbItemParamItem itemParamItem = itemParamItemServiceProxy.loadItemParamItemListService(itemId);
            result = new EgoResult(itemParamItem);
//            result.setStatus(200);
//            result.setData(itemParamItem);
        } catch (Exception e) {
            System.out.println("##############加载商品规格参数信息失败################");
            e.printStackTrace();
        }
        return result;
    }
}
