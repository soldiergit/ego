package com.soldier.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
    public PageResult<TbItemParam> loadTbItemParamListService(Integer page, Integer rows) {

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
}
