package com.soldier.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.mapper.TbItemDescMapper;
import com.soldier.ego.rpc.mapper.TbItemMapper;
import com.soldier.ego.rpc.mapper.TbItemParamItemMapper;
import com.soldier.ego.rpc.pojo.*;
import com.soldier.ego.rpc.pojo.TbItemExample.Criteria;
import com.soldier.ego.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-8-21上午11:43
 * @Describe:商品信息接口
 **/
@Service
public class ItemServiceImpl implements ItemService {

    //注入mapper接口代理对象
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public PageResult<TbItem> selectItemListService(Integer page, Integer rows) {

        //执行分页操作
        Page ps = PageHelper.startPage(page, rows);

        TbItemExample example = new TbItemExample();

        //执行数据库查询操作
        List<TbItem> tbItemList = tbItemMapper.selectByExample(example);

        PageResult<TbItem> result = new PageResult<TbItem>();
        result.setRows(tbItemList);
        result.setTotal(ps.getTotal());

        return result;
    }

    @Override
    public EgoResult updateItemStatusService(List<Long> itemIds, Boolean flag) {

        //创建tbItem对象
        TbItem item = new TbItem();
        if (flag) {
            item.setStatus((byte) 1);
        } else {
            item.setStatus((byte) 2);
        }

        //动态产生where条件
        TbItemExample example=new TbItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdIn(itemIds);

        //where id in (?,?,?）
        tbItemMapper.updateByExample(item, example);

        //where id = ?
        //tbItemMapper.updateByPrimaryKey()

        return EgoResult.ok();
    }

    @Override
    public EgoResult deleteItemService(List<Long> itemIds) {

        //动态产生where条件
        TbItemExample example = new TbItemExample();
        Criteria criteria = example.createCriteria();
        criteria.andIdIn(itemIds);

        //where id in (?,?,?）
        tbItemMapper.deleteByExample(example);

        //where id = ?
        //tbItemMapper.deleteByPrimaryKey();

        return EgoResult.ok();
    }

    @Override
    public EgoResult saveItemService(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem) {

        tbItemMapper.insert(item);
        tbItemDescMapper.insert(itemDesc);
        tbItemParamItemMapper.insert(itemParamItem);
        return EgoResult.ok();
    }

    @Override
    public EgoResult updateItemService(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem) {

        /**
         * 更新商品基本信息
         *  updateByPrimaryKey          --更新表中的所有字段
         *  updateByPrimaryKeySelective --如果对象中的某个特定属性是空的，不会去更新那一个列，比如说商品不一定有描述信息
         */
        tbItemMapper.updateByPrimaryKeySelective(item);

        /**
         * 判断该商品是否有描述信息
         */
        //查询某个商品的描述信息
        TbItemDescExample itemDescExample = new TbItemDescExample();
        TbItemDescExample.Criteria itemDescCriteria = itemDescExample.createCriteria();
        // where itemId = ?
        itemDescCriteria.andItemIdEqualTo(itemDesc.getItemId());
        Integer rows = tbItemDescMapper.countByExample(itemDescExample);

        //更新商品基本信息
        if (rows == 0) {
            this.tbItemDescMapper.insert(itemDesc);
        } else {
            //如果是更新，create字段不会改变；因为updateByPrimaryKeySelective不会更新对象中为空的字段
            itemDesc.setCreated(null);
            this.tbItemDescMapper.updateByPrimaryKeySelective(itemDesc);
        }

        //更新商品规格参数信息
        TbItemParamItemExample itemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria itemParamItemCriteria = itemParamItemExample.createCriteria();
        // where itemId = ?
        itemParamItemCriteria.andItemIdEqualTo(itemParamItem.getItemId());
        tbItemParamItemMapper.updateByExampleSelective(itemParamItem, itemParamItemExample);

        return EgoResult.ok();
    }

    @Override
    public TbItem selectItemById(Long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }
}
