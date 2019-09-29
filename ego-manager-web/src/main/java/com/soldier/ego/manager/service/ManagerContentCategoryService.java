package com.soldier.ego.manager.service;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.TreeNode;
import com.soldier.ego.rpc.pojo.TbContentCategory;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-19下午4:43
 * @Describe:网站内容分类(树)
 **/
public interface ManagerContentCategoryService {

    /**
     * 根据父id加载所有网站内容分类(树)
     * @param pId   父id
     */
    public List<TreeNode> loadContentCategoryByPid(Long pId);

    /**
     * 保存内容分类节点
     */
    public EgoResult saveContentCategory(TbContentCategory contentCategory);

    /**
     * 删除内容分类节点
     */
    public void deleteContentCategory(Long id);
//    public void deleteContentCategory(TbContentCategory contentCategory);

    /**
     * 修改内容分类节点
     */
    public EgoResult updateContentCategory(TbContentCategory contentCategory);
}
