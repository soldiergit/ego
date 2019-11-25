package com.soldier.ego.portal.service;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-10-4下午7:16
 * @Describe:网站门户-网站内容接口
 **/
public interface PortalContentService {

    /**
     * 网站门户-根据内容分类id加载其所有网站内容
     */
    public String loadContentListByCid(Long categoryId);

}
