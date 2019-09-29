package com.soldier.ego.rpc.service.impl;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.rpc.mapper.TbContentCategoryMapper;
import com.soldier.ego.rpc.pojo.TbContentCategory;
import com.soldier.ego.rpc.pojo.TbContentCategoryExample;
import com.soldier.ego.rpc.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-19下午4:35
 * @Describe:网站内容分类(树)接口
 **/
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    //注入mapper接口代理对象
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> loadContentCategoryByPidService(Long pId) {

        //因为不是根据主键id查，所以要新建example对象
        //动态产生where条件
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();

        //where parent_id = ?
        criteria.andParentIdEqualTo(pId);

        return tbContentCategoryMapper.selectByExample(example);
    }

    @Override
    public EgoResult saveContentCategoryService(TbContentCategory contentCategory) {

        EgoResult result = null;
        try {
            //更新当前添加节点的父节点的is_parent为1
            TbContentCategory parent = new TbContentCategory();
            parent.setId(contentCategory.getParentId());
            parent.setIsParent(true);
            parent.setUpdated(contentCategory.getCreated());
            //updateByPrimaryKeySelective不会更新对象中为空的字段
            tbContentCategoryMapper.updateByPrimaryKeySelective(parent);

            //添加内容分类节点
            tbContentCategoryMapper.insert(contentCategory);

            result = new EgoResult();
            result.setStatus(200);
            result.setData(contentCategory);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("##############新增内容分类节点失败################");

        }
        return result;
    }

    @Override
    public void deleteContentCategoryService(Long id) {
        try {
            //获得当前点击删除节点的信息
            TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);

            //判断点击节点是否还有其它亲兄弟
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            //where parent_id=?
            criteria.andParentIdEqualTo(contentCategory.getParentId());
            int count = tbContentCategoryMapper.countByExample(example);
            //如果点击节点的父亲只有它一个儿子
            if (count == 1) {
                TbContentCategory parent = new TbContentCategory();
                parent.setId(contentCategory.getParentId());
                parent.setIsParent(false);//parent将不再是父亲
                //updateByPrimaryKeySelective不会更新对象中为空的字段
                tbContentCategoryMapper.updateByPrimaryKeySelective(parent);
            }

            //删除当前点击节点
            tbContentCategoryMapper.deleteByPrimaryKey(id);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("##############新增内容分类节点失败################");

        }
    }

    @Override
    public EgoResult updateContentCategoryService(TbContentCategory contentCategory) {

        EgoResult result = null;
        try {
            //updateByPrimaryKeySelective不会更新对象中为空的字段
            tbContentCategoryMapper.updateByPrimaryKeySelective(contentCategory);

            result = new EgoResult();
            result.setStatus(200);
            result.setData(contentCategory);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("##############修改内容分类节点失败################");

        }
        return result;
    }
}
