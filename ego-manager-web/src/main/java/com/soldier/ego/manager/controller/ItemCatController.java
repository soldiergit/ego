package com.soldier.ego.manager.controller;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.TreeNode;
import com.soldier.ego.manager.service.ManagerItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-4下午4:20
 * @Describe:商品类目
 **/
@Controller
public class ItemCatController {

    //注入service对象
    @Autowired
    private ManagerItemCatService managerItemCatService;

    /**
     * 处理加载商品类目的请求
     *      默认加载一级类目
     *      required = false --> 非必须
     */
    @RequestMapping(value = "/item/cat/list", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public List<TreeNode> itemCatList(@RequestParam(defaultValue = "0", required = false) Long id) {
        return managerItemCatService.getItemCatList(id);
    }

    /**
     * 处理根据id查询商品类目的请求
     */
    @RequestMapping(value = "/item/cat/query/{id}", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult queryItemCat(@PathVariable Long id) {
        return managerItemCatService.loadItemCatById(id);
    }
}
