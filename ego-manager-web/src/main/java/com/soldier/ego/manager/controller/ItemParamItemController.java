package com.soldier.ego.manager.controller;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.manager.service.ManagerItemParamItemService;
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
 * @create:19-9-17下午5:28
 * @Describe:商品规格参数信息
 **/
@Controller
public class ItemParamItemController {

    //注入service对象
    @Autowired
    private ManagerItemParamItemService managerItemParamItemService;

    /**
     * 处理根据商品id查询商品规格参数信息的请求
     */
    @RequestMapping(value = "param/item/query/{itemId}", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult queryItemParamItem(@PathVariable Long itemId) {
        return managerItemParamItemService.loadItemParamItemList(itemId);
    }
}
