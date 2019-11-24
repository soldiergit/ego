package com.soldier.ego.search.controller;

import com.soldier.ego.search.service.PortalItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-10-4下午6:53
 * @Describe:网站门户-商品类目
 **/
@Controller
public class PortalItemCatController {

    //注入service对象
    @Autowired
    private PortalItemCatService portalItemCatService;

    /**
     * 处理加载(网站门户)商品类目的请求
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping(value = "/item/cat", produces = MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
    @ResponseBody
    public String itemCat() {
        return portalItemCatService.loadItemCatList();
    }
}
