package com.soldier.ego.manager.service.impl;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.manager.service.ManagerContentService;
import com.soldier.ego.rpc.pojo.TbContent;
import com.soldier.ego.rpc.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-29上午10:58
 * @Describe:网站内容
 **/
@Service
public class ManagerContentServiceImpl implements ManagerContentService {

    //注入的是远程服务的代理对象
    @Autowired
    private ContentService contentServiceProxy;

    @Override
    public PageResult<TbContent> loadContentList(Long categoryId, Integer page, Integer rows) {
        return contentServiceProxy.loadContentListService(categoryId, page, rows);
    }

    @Override
    public EgoResult saveContent(TbContent content) {
        return contentServiceProxy.saveContentService(content);
    }

    @Override
    public EgoResult deleteContent(Long[] ids) {
        //将ids数组转为List集合
        List<Long> contentIds = Arrays.asList(ids);
        //调用远程服务
        return contentServiceProxy.deleteContentService(contentIds);
    }

    @Override
    public EgoResult editContent(TbContent content) {
        return contentServiceProxy.editContentService(content);
    }
}
