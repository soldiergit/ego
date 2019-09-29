package com.soldier.ego.manager.service.impl;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.IDUtils;
import com.soldier.ego.beans.TreeNode;
import com.soldier.ego.manager.service.ManagerContentCategoryService;
import com.soldier.ego.rpc.pojo.TbContentCategory;
import com.soldier.ego.rpc.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-19下午4:44
 * @Describe:网站内容分类(树)
 **/
@Service
public class ManagerContentCategoryServiceImpl implements ManagerContentCategoryService {

    //注入的是远程服务的代理对象
    @Autowired
    private ContentCategoryService contentCategoryServiceProxy;

    @Override
    public List<TreeNode> loadContentCategoryByPid(Long pId) {

        List<TreeNode> list = new ArrayList<>();
        List<TbContentCategory> contentCategoryList = contentCategoryServiceProxy.loadContentCategoryByPidService(pId);

        for (TbContentCategory contentCategory : contentCategoryList) {
            TreeNode node = new TreeNode();
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            // 如果是父节点，关闭，否则打开
            node.setState(contentCategory.getIsParent()? "closed" : "open");
            list.add(node);
        }

        return list;
    }

    @Override
    public EgoResult saveContentCategory(TbContentCategory contentCategory) {

        //生成id  和   时间
        Long id = IDUtils.genItemId();
        Date date = new Date();

        //新增的节点非父节点
        contentCategory.setId(id);
        contentCategory.setCreated(date);
        contentCategory.setUpdated(date);
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategory.setIsParent(false);

        return contentCategoryServiceProxy.saveContentCategoryService(contentCategory);
    }

    @Override
    public void deleteContentCategory(Long id) {
        contentCategoryServiceProxy.deleteContentCategoryService(id);
    }

    @Override
    public EgoResult updateContentCategory(TbContentCategory contentCategory) {
        contentCategory.setUpdated(new Date());
        return contentCategoryServiceProxy.updateContentCategoryService(contentCategory);
    }
}
