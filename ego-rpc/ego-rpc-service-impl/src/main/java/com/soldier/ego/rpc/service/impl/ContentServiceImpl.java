package com.soldier.ego.rpc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.mapper.TbContentMapper;
import com.soldier.ego.rpc.pojo.TbContent;
import com.soldier.ego.rpc.pojo.TbContentExample;
import com.soldier.ego.rpc.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-20上午11:56
 * @Describe:网站内容接口
 **/
@Service
public class ContentServiceImpl implements ContentService {

    //注入mapper接口代理对象
    @Autowired
    private TbContentMapper tbContentMapper;

    @Override
    public PageResult<TbContent> loadContentListService(Long categoryId, Integer page, Integer rows) {

        PageResult<TbContent> result = new PageResult<TbContent>();

        try {

            //执行分页操作
            Page ps = PageHelper.startPage(page, rows);

            //动态产生where条件
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(categoryId);
            List<TbContent> contentList = tbContentMapper.selectByExampleWithBLOBs(example);

            result.setRows(contentList);
            result.setTotal(ps.getTotal());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("##############根据网站内容分类id加载网站内容失败################");
        }

        return result;
    }

    @Override
    public EgoResult saveContentService(TbContent content) {
        try {
            Date date = new Date();
            content.setCreated(date);
            content.setUpdated(date);
            tbContentMapper.insert(content);
            return EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("##############新增网站内容失败################");
        }
        return null;
    }

    @Override
    public EgoResult deleteContentService(List<Long> ids) {
        try {
            //动态产生where条件
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);

            //where id in (?,?,?）
            tbContentMapper.deleteByExample(example);
            return EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("##############批量删除网站内容失败################");
        }
        return null;
    }

    @Override
    public EgoResult editContentService(TbContent content) {
        try {
            /**
             * 更新网站内容
             *  updateByPrimaryKey          --更新表中的所有字段
             *  updateByPrimaryKeySelective --如果对象中的某个特定属性是空的，不会去更新那一个列，比如说商品不一定有描述信息
             */
            content.setUpdated(new Date());
            tbContentMapper.updateByPrimaryKeySelective(content);
            return  EgoResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("##############更新网站内容失败################");
        }
        return null;
    }

    @Override
    public List<TbContent> loadContentListByCidService(Long categoryId) {
        try {
            //动态产生where条件
            TbContentExample example = new TbContentExample();

            //where category_id = ?
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(categoryId);
            return tbContentMapper.selectByExampleWithBLOBs(example);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("##############网站门户-加载首页的商品类目失败################");
        }
        return null;
    }
}
