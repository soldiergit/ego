package com.soldier.ego.manager.controller;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.manager.service.ManagerContentService;
import com.soldier.ego.rpc.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-29上午11:07
 * @Describe:网站内容
 **/
@Controller
public class ContentController {

    //注入service对象
    @Autowired
    private ManagerContentService managerContentService;

    /**
     * 处理网站内容分页查询的请求
     */
    @RequestMapping(value = "/content/query/list", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public PageResult<TbContent> contentQueryList(Long categoryId, Integer page, Integer rows) {
        return managerContentService.loadContentList(categoryId, page, rows);
    }

    /**
     * 处理新增网站内容的请求
     */
    @RequestMapping(value = "/content/save", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult contentSave(TbContent content) {
        return managerContentService.saveContent(content);
    }

    /**
     * 处理批量删除网站内容的请求
     */
    @RequestMapping(value = "/content/delete", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult deleteItem(Long[] ids) {
        return managerContentService.deleteContent(ids);
    }

    /**
     * 处理修改网站内容的请求
     */
    @RequestMapping(value = "/content/edit", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult contentEdit(TbContent content) {
        return managerContentService.editContent(content);
    }
}
