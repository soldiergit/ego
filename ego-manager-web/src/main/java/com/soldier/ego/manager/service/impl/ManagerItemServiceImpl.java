package com.soldier.ego.manager.service.impl;

import com.soldier.ego.beans.PageResult;
import com.soldier.ego.manager.service.ManagerItemService;
import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-21 下午4:39
 * @Describe:
 **/
@Service
public class ManagerItemServiceImpl implements ManagerItemService {

    //注入的是远程服务的代理对象
    @Autowired
    private ItemService itemServiceProxy;

    @Override
    public PageResult<TbItem> selectItemList(Integer page, Integer rows) {
        return itemServiceProxy.selectItemList(page, rows);
    }
}
