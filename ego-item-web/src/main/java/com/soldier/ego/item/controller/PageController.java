package com.soldier.ego.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25上午8:21
 * @Describe:页面跳转
 **/
@Controller
public class PageController {

    /**
     * url为空时默认为index
     */
    @RequestMapping("/{url}")
    public String loadPage(@PathVariable String url) {
        return url;
    }
}
