package com.soldier.ego.rpc.service.impl;

import com.soldier.ego.rpc.mapper.TbItemParamItemMapper;
import com.soldier.ego.rpc.pojo.TbItemParamItem;
import com.soldier.ego.rpc.pojo.TbItemParamItemExample;
import com.soldier.ego.rpc.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-17下午5:07
 * @Describe:商品规格参数信息接口
 **/
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

    //注入mapper接口代理对象
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItemParamItem loadItemParamItemListService(Long itemId) {

        TbItemParamItemExample example = new TbItemParamItemExample();

        //封装查询条件
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);

        List<TbItemParamItem> itemParamItemList = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        System.out.println(itemParamItemList);

        if (itemParamItemList!=null && itemParamItemList.size()==1) return itemParamItemList.get(0);
        return null;
    }
}
