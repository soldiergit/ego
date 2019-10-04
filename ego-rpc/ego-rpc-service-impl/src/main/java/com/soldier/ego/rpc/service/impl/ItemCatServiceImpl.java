package com.soldier.ego.rpc.service.impl;

import com.soldier.ego.rpc.mapper.TbItemCatMapper;
import com.soldier.ego.rpc.pojo.TbItemCat;
import com.soldier.ego.rpc.pojo.TbItemCatExample;
import com.soldier.ego.rpc.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-4下午3:59
 * @Describe:商品类目接口
 **/
@Service
public class ItemCatServiceImpl implements ItemCatService {

    //注入mapper接口代理对象
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> getItemCatListByParentId(Long id) {

        //因为不是根据主键id查，所以要新建example对象
        //动态产生where条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();

        //where parent_id = ?
        criteria.andParentIdEqualTo(id);

        List<TbItemCat> itemCatList = tbItemCatMapper.selectByExample(example);

        return itemCatList;
    }

    @Override
    public TbItemCat loadItemCatByIdService(Long id) {

        TbItemCat itemCat = tbItemCatMapper.selectByPrimaryKey(id);

        return itemCat;
    }

    @Override
    public List<TbItemCat> loadItemCatListService() {
        //动态产生where条件
        TbItemCatExample example = new TbItemCatExample();
        return tbItemCatMapper.selectByExample(example);
    }
}
