package com.soldier.ego.search.controller;

import com.soldier.ego.search.service.SearchItemDescService;
import com.soldier.ego.search.service.SearchItemParamItemService;
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
 * @create:19-11-22下午5:00
 * @Describe:检索商品规格参数信息
 **/
@Controller
public class SearchItemParamItemController {

    @Autowired
    private SearchItemParamItemService searchItemParamItemService;

    /**
     * 处理加载商品规格参数信息的请求
     *      MediaType.TEXT_HTML_VALUE+";charset=UTF-8"  --设置响应头编码，解决响应数据乱码
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping(value = "/item/param/{itemId}", produces = MediaType.TEXT_HTML_VALUE+";charset=UTF-8")
    @ResponseBody
    public String loadItemParamItem(@PathVariable Long itemId) {
        return searchItemParamItemService.loadItemParamItemService(itemId);
    }
}
