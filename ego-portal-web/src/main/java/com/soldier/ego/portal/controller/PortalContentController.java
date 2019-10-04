package com.soldier.ego.portal.controller;

import com.soldier.ego.portal.service.PortalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-10-4下午7:27
 * @Describe:网站门户-网站内容
 **/
@Controller
public class PortalContentController {

    //注入service对象
    @Autowired
    private PortalContentService portalContentService;

    /**
     * 处理加载(网站门户)大广告位内容的请求
     */
    @RequestMapping(value = "/content/index/list", produces = MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
    @ResponseBody
    public String contentIndexList(Long categoryId) {
        return portalContentService.loadContentListByCid(categoryId);
    }
}
