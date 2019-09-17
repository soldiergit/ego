package com.soldier.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.mapper.TbItemParamMapper;
import com.soldier.ego.rpc.pojo.TbItemParam;
import com.soldier.ego.rpc.pojo.TbItemParamExample;
import com.soldier.ego.rpc.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-10上午9:27
 * @Describe:商品规格参数模板接口
 **/
@Service
public class ItemParamServiceImpl implements ItemParamService {

    //注入mapper接口代理对象
    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public PageResult<TbItemParam> loadItemParamListService(Integer page, Integer rows) {

        //执行分页操作
        Page ps = PageHelper.startPage(page, rows);

        TbItemParamExample example = new TbItemParamExample();

        //执行数据库查询操作
        //selectByExampleWithBLOBs适用于查询大字段，因为表中有text字段
        List<TbItemParam> tbItemParamList = tbItemParamMapper.selectByExampleWithBLOBs(example);

        PageResult<TbItemParam> result = new PageResult<TbItemParam>();
        result.setRows(tbItemParamList);
        result.setTotal(ps.getTotal());

        return result;

    }

    @Override
    public TbItemParam loadItemParamByCidService(Long cid) {

        TbItemParamExample example = new TbItemParamExample();

        //封装查询条件
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);

        //执行数据库查询操作
        //selectByExampleWithBLOBs适用于查询大字段，因为表中有text字段
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        //因为一个商品类目(itemCat)对应一个商品规格参数模板(itemParam)，1：1
        if (list!=null && list.size()==1) return list.get(0);

        return null;
    }

    @Override
    public EgoResult saveItemParamService(TbItemParam itemParam) {

        try {
            tbItemParamMapper.insert(itemParam);
            return EgoResult.ok();
        } catch (Exception e) {
            System.out.println("##############新增商品规格参数模板失败################");
        }

        return null;
    }

    @Override
    public EgoResult deleteItemParamService(List<Long> ids) {

        try {
            TbItemParamExample example = new TbItemParamExample();
            TbItemParamExample.Criteria criteria = example.createCriteria();

            //添加删除条件 where id in (?,?,?...)
            criteria.andIdIn(ids);

            //执行删除
            tbItemParamMapper.deleteByExample(example);
            return EgoResult.ok();
        } catch (Exception e) {
            System.out.println("##############批量删除商品规格参数模板失败################");
        }

        return null;
    }
}
