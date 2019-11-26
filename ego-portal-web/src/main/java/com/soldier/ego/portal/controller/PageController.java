package com.soldier.ego.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-10-4下午6:12
 * @Describe:页面跳转
 **/
@Controller
public class PageController {

    /**
     * 进行页面跳转
     * url为空时默认为index
     */
    @RequestMapping("/{url}")
    public String loadPage(@PathVariable String url) {
        return url;
    }
}
