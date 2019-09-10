package com.soldier.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.mapper.TbItemDescMapper;
import com.soldier.ego.rpc.mapper.TbItemMapper;
import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.rpc.pojo.TbItemDesc;
import com.soldier.ego.rpc.pojo.TbItemDescExample;
import com.soldier.ego.rpc.pojo.TbItemExample;
import com.soldier.ego.rpc.pojo.TbItemExample.Criteria;
import com.soldier.ego.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-21 上午11:43
 * @Describe: 商品信息接口
 **/
@Service
public class ItemServiceImpl implements ItemService {

    //注入mapper接口代理对象
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public PageResult<TbItem> selectItemList(Integer page, Integer rows) {

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
    public EgoResult updateItemStatus(List<Long> itemIds, Boolean flag) {

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
    public EgoResult deleteItem(List<Long> itemIds) {

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
    public EgoResult saveItem(TbItem item, TbItemDesc itemDesc) {

        tbItemMapper.insert(item);
        tbItemDescMapper.insert(itemDesc);
        return EgoResult.ok();
    }

    @Override
    public EgoResult updateItem(TbItem item, TbItemDesc itemDesc) {

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
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        // where itemId = ?
        criteria.andItemIdEqualTo(itemDesc.getItemId());
        Integer rows = tbItemDescMapper.countByExample(example);
        //更新商品基本信息
        if (rows == 0) {
            this.tbItemDescMapper.insert(itemDesc);
        } else {
            //如果是更新，create字段不会改变；因为updateByPrimaryKeySelective不会更新对象中为空的字段
            itemDesc.setCreated(null);
            this.tbItemDescMapper.updateByPrimaryKeySelective(itemDesc);
        }

        return EgoResult.ok();
    }
}
