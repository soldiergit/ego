package com.soldier.ego.search.controller;

import com.soldier.ego.search.service.SearchItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-22下午5:00
 * @Describe:检索商品描述信息
 **/
@Controller
public class SearchItemDescController {

    @Autowired
    private SearchItemDescService searchItemDescService;

    /**
     * 处理加载商品基本信息的请求
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping("/item/desc/{itemId}")
    @ResponseBody
    public String loadItemDesc(@PathVariable Long itemId) {
        return searchItemDescService.loadItemDescService(itemId);
    }
}
