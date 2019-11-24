package com.soldier.ego.manager.controller;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.manager.service.ManagerItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-9下午4:29
 * @Describe:商品描述
 **/
@Controller
public class ItemDescController {

    //注入service对象
    @Autowired
    private ManagerItemDescService managerItemDescService;

    /**
     * 加载商品描述信息的请求
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping(value = "query/item/desc/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult itemDesc(@PathVariable Long itemId) {
        return managerItemDescService.getItemDesc(itemId);
    }
}
