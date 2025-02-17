package com.soldier.ego.manager.controller;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.manager.service.ManagerItemService;
import com.soldier.ego.rpc.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-8-21下午4:14
 * @Describe:商品信息
 **/
@Controller
public class ItemController {

    //注入service对象
    @Autowired
    private ManagerItemService managerItemService;

    /**
     * 处理商品信息分页查询的请求
     *      默认第1页，30条记录
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping(value = "item/list", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public PageResult<TbItem> itemList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows) {
        return managerItemService.selectItemList(page, rows);
    }

    /**
     * 商品信息的上架请求
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping(value = "item/reshelf", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult reshelfItem(Long[] ids) {
        return managerItemService.reshelfItem(ids);
    }

    /**
     * 商品信息的下架请求
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping(value = "item/instock", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult instockItem(Long[] ids) {
        return managerItemService.instockItem(ids);
    }

    /**
     * 商品信息的删除请求
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping(value = "item/delete", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult deleteItem(Long[] ids) {
        return managerItemService.deleteItem(ids);
    }

    /**
     * 商品信息的发布请求
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping(value = "item/save", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult saveItem(TbItem item, String desc, String itemParams) {
        return managerItemService.saveItem(item, desc, itemParams);
    }

    /**
     * 商品信息的更新请求
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping(value = "item/update", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult updateItem(TbItem item, String desc, String itemParams) {
        return managerItemService.updateItem(item, desc, itemParams);
    }
}
