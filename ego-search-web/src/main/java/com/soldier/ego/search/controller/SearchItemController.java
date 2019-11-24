package com.soldier.ego.search.controller;

import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.search.entity.SearchResult;
import com.soldier.ego.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-22下午5:00
 * @Describe:检索商品基本信息
 **/
@Controller
public class SearchItemController {

    @Autowired
    private SearchItemService searchItemService;

    /**
     * 进行页面跳转
     */
    @RequestMapping("/{url}")
    public String loadPage(@PathVariable String url, String key,
                           @RequestParam(defaultValue = "1") Integer page, Model model) {

        //  解码，防止乱码，因为是通过路径传参
        String item_keywords = null;
        try {
            item_keywords = new String(key.getBytes("ISO-8859-1"), "UTF-8");
            //  调研service接口
            SearchResult result = searchItemService.loadItemService(item_keywords, page);

            //  EL表达式取值
            model.addAttribute("query", item_keywords);
            model.addAttribute("itemList", result.getItemList());
            model.addAttribute("totalPages", result.getTotalPages());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * 处理加载商品基本信息的请求
     */
    @RequestMapping("/item/{itemId}")
    public String loadItem(@PathVariable Long itemId, Model model) {
        TbItem item = searchItemService.loadItemService(itemId);

        //  将查到的数据放到模型对象里
        model.addAttribute("item", item);

        return "item";
    }
}
