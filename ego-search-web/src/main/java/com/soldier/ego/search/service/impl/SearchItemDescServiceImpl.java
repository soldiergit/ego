package com.soldier.ego.search.service.impl;

import com.soldier.ego.rpc.service.ItemDescService;
import com.soldier.ego.search.service.SearchItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-24上午8:56
 * @Describe:
 **/
@Service
public class SearchItemDescServiceImpl implements SearchItemDescService {

    //注入的是远程服务的代理对象
    @Autowired
    private ItemDescService itemDescServiceProxy;

    @Override
    public String loadItemDescService(Long itemId) {
        return itemDescServiceProxy.getItemDescService(itemId).getItemDesc();
    }
}
