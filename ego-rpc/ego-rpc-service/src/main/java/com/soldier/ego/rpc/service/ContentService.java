package com.soldier.ego.rpc.service;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.pojo.TbContent;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-20上午11:51
 * @Describe:网站内容接口
 **/
public interface ContentService {

    /**
     * 根据内容分类id加载其所有网站内容
     * @param categoryId    内容分类节点id
     * @param page          几1页，
     * @param rows          显示记录数
     */
    public PageResult<TbContent> loadContentListService(Long categoryId, Integer page, Integer rows);

    /**
     * 新增网站内容
     */
    public EgoResult saveContentService(TbContent content);

    /**
     * 删除网站内容
     */
    public EgoResult deleteContentService(List<Long> ids);

    /**
     * 修改网站内容
     */
    public EgoResult editContentService(TbContent content);
}
