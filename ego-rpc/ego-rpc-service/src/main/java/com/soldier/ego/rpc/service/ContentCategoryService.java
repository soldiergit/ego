package com.soldier.ego.rpc.service;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.rpc.pojo.TbContentCategory;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-19下午4:29
 * @Describe:网站内容分类(树)接口
 **/
public interface ContentCategoryService {

    /**
     * 根据父id加载所有网站内容分类(树)
     * @param pId   父id
     */
    public List<TbContentCategory> loadContentCategoryByPidService(Long pId);

    /**
     * 保存内容分类节点
     */
    public EgoResult saveContentCategoryService(TbContentCategory contentCategory);

    /**
     * 删除内容分类节点
     */
    public void deleteContentCategoryService(Long id);
//    public void deleteContentCategoryService(TbContentCategory contentCategory);

    /**
     * 修改内容分类节点
     */
    public EgoResult updateContentCategoryService(TbContentCategory contentCategory);
}
