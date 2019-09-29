package com.soldier.ego.manager.controller;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.TreeNode;
import com.soldier.ego.manager.service.ManagerContentCategoryService;
import com.soldier.ego.rpc.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-19下午4:55
 * @Describe:网站内容分类(树)
 **/
@Controller
public class ContentCategoryController {

    //注入service对象
    @Autowired
    private ManagerContentCategoryService managerContentCategoryService;

    /**
     * 处理加载网站内容分类(树)的请求
     *      默认加载一级类目
     */
    @RequestMapping(value = "/content/category/list", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public List<TreeNode> contentCategoryList(@RequestParam(defaultValue = "0") Long id) {
        return managerContentCategoryService.loadContentCategoryByPid(id);
    }

    /**
     * 处理新增内容分类节点的请求
     */
    @RequestMapping(value = "/content/category/create", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult saveContentCategory(TbContentCategory contentCategory) {
        return managerContentCategoryService.saveContentCategory(contentCategory);
    }

    /**
     * 处理删除内容分类节点的请求
     */
    @RequestMapping(value = "/content/category/delete", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public void deleteContentCategory(Long id) {
        managerContentCategoryService.deleteContentCategory(id);
    }
//    @RequestMapping(value = "/content/category/delete", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
//    @ResponseBody
//    public EgoResult deleteContentCategory(TbContentCategory contentCategory) {
//        return managerContentCategoryService.saveContentCategory(contentCategory);
//    }

    /**
     * 处理修改内容分类节点的请求
     */
    @RequestMapping(value = "/content/category/update", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult updateContentCategory(TbContentCategory contentCategory) {
        return managerContentCategoryService.updateContentCategory(contentCategory);
    }
}
