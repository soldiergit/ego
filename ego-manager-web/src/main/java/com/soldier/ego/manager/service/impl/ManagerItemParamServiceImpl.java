package com.soldier.ego.manager.service.impl;

import com.soldier.ego.beans.PageResult;
import com.soldier.ego.manager.service.ManagerItemParamService;
import com.soldier.ego.rpc.pojo.TbItemParam;
import com.soldier.ego.rpc.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-10上午9:47
 * @Describe:商品规格参数模板
 **/
@Service
public class ManagerItemParamServiceImpl implements ManagerItemParamService {

    //注入的是远程服务的代理对象
    @Autowired
    private ItemParamService itemParamServiceProxy;

    @Override
    public PageResult<TbItemParam> loadTbItemParamList(Integer page, Integer rows) {
        return itemParamServiceProxy.loadTbItemParamListService(page, rows);
    }
}
