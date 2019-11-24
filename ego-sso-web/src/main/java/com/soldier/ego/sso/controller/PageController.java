package com.soldier.ego.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-24上午10:01
 * @Describe:页面跳转
 **/
@Controller
public class PageController {

    @RequestMapping("/{url}")
    public String loadPage(@PathVariable String url) {
        return url;
    }
}
