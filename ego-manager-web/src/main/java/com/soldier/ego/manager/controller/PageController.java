package com.soldier.ego.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-8-21上午10:01
 * @Describe:页面跳转的controller
 **/
@Controller
public class PageController {

    /**
     * 加载商城后台系统的首页
     */
    @RequestMapping(value = { "", "/" })
    public String showIndex() {
        System.out.println("首页");
        return "index";
    }

    /**
     * 加载其它的视图
     */
    @RequestMapping("{page}")
    public String showPage(@PathVariable String page) {
        return page;
    }
}
