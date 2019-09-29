package com.soldier.ego.manager.service;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.pojo.TbContent;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-29上午10:55
 * @Describe:网站内容接口
 **/
public interface ManagerContentService {

    /**
     * 根据内容分类id，进行内容的分页查询
     * @param categoryId    内容分类节点id
     * @param page          几1页，
     * @param rows          显示记录数
     */
    public PageResult<TbContent> loadContentList(Long categoryId, Integer page, Integer rows);

    /**
     * 新增网站内容
     */
    public EgoResult saveContent(TbContent content);

    /**
     * 批量删除网站内容
     */
    public EgoResult deleteContent(Long[] ids);

    /**
     * 修改网站内容
     */
    public EgoResult editContent(TbContent content);
}
