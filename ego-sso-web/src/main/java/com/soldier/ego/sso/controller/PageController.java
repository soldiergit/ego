package com.soldier.ego.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-24上午10:01
 * @Describe:页面跳转
 **/
@Controller
public class PageController {

    /**
     * required = false --> 非必须
     * @param url       跳转的路径
     * @param redirect  用户登录成功后需要跳转的路径（详情看ego-item-web里的LoginIntercptor和login.jsp）
     */
    @RequestMapping("/{url}")
    public String loadPage(@PathVariable String url,
                           @RequestParam(required = false) String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return url;
    }
}
