package com.soldier.ego.manager.controller;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.manager.service.ManagerItemParamService;
import com.soldier.ego.rpc.pojo.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-10上午9:50
 * @Describe:商品规格参数模板
 **/
@Controller
public class ItemParamController {

    //注入service对象
    @Autowired
    private ManagerItemParamService managerItemParamService;

    /**
     * 处理商品规格参数模板分页查询的请求
     *      默认第1页，30条记录
     */
    @RequestMapping(value = "item/param/list", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public PageResult<TbItemParam> itemList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows) {
        return managerItemParamService.loadTbItemParamList(page, rows);
    }

    /**
     * 处理根据商品类目id查询商品规格参数模板的请求
     */
    @RequestMapping(value = "item/param/query/itemcatid/{cid}", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult queryParam(@PathVariable Long cid) {
        return managerItemParamService.loadTbItemParamByCid(cid);
    }

    /**
     * 处理商品规格参数模板的新增请求
     */
    @RequestMapping(value = "item/param/save/{cid}", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult saveItemParam(@PathVariable Long cid, String paramData) {
        return managerItemParamService.saveItemParam(cid, paramData);
    }

    /**
     * 处理商品规格参数模板的批量删除请求
     */
    @RequestMapping(value = "item/param/delete", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public EgoResult deleteItemParam(Long[] ids) {
        return managerItemParamService.deleteItemParam(ids);
    }
}
