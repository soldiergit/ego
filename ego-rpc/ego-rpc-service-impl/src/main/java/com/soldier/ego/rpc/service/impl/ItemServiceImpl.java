package com.soldier.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.mapper.TbItemMapper;
import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.rpc.pojo.TbItemExample;
import com.soldier.ego.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-21 上午11:43
 * @Describe:
 **/
@Service
public class ItemServiceImpl implements ItemService {

    //注入mapper接口代理对象
    @Autowired
    private TbItemMapper tbItemMapper;

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
}
