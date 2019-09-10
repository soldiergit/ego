package com.soldier.ego.rpc.service.impl;

import com.soldier.ego.rpc.mapper.TbItemDescMapper;
import com.soldier.ego.rpc.pojo.TbItemDesc;
import com.soldier.ego.rpc.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-9下午4:26
 * @Describe:商品描述信息接口
 **/
@Service
public class ItemDescServiceImpl implements ItemDescService {

    //注入mapper接口代理对象
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public TbItemDesc getItemDescService(Long itemId) {
        return tbItemDescMapper.selectByPrimaryKey(itemId);
    }
}
