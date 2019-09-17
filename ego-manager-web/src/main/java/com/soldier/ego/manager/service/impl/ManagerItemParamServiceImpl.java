package com.soldier.ego.manager.service.impl;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.manager.service.ManagerItemParamService;
import com.soldier.ego.rpc.pojo.TbItemParam;
import com.soldier.ego.rpc.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-10上午9:47
 * @Describe:商品规格参数模板
 **/
@Service
public class ManagerItemParamServiceImpl implements ManagerItemParamService {

    //注入的是远程服务的代理对象
    @Autowired
    private ItemParamService itemParamServiceProxy;

    @Override
    public PageResult<TbItemParam> loadTbItemParamList(Integer page, Integer rows) {
        //调用远程服务代理对象
        return itemParamServiceProxy.loadItemParamListService(page, rows);
    }

    @Override
    public EgoResult loadTbItemParamByCid(Long cid) {
        EgoResult result = null;
        try {
            //调用远程服务代理对象
            TbItemParam tbItemParam = itemParamServiceProxy.loadItemParamByCidService(cid);
            result = new EgoResult(tbItemParam);
//            result.setStatus(200);
//            result.setData(tbItemParam);
        } catch (Exception e) {
            System.out.println("##############加载商品规格参数模板失败################");
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public EgoResult saveItemParam(Long cid, String paramData) {

        //当前时间
        Date date = new Date();
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        itemParam.setCreated(date);
        itemParam.setUpdated(date);

        //调用远程服务代理对象
        return itemParamServiceProxy.saveItemParamService(itemParam);
    }

    @Override
    public EgoResult deleteItemParam(Long[] ids) {
        //将ids数组转为List集合
        List<Long> itemParamIds = Arrays.asList(ids);
        //调用远程服务代理对象
        return itemParamServiceProxy.deleteItemParamService(itemParamIds);
    }
}
