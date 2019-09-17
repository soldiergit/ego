package com.soldier.ego.manager.service;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.pojo.TbItemParam;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-10上午9:45
 * @Describe:商品规格参数模板
 **/
public interface ManagerItemParamService {

    /**
     * 商品规格参数模板的分页显示
     */
    public PageResult<TbItemParam> loadTbItemParamList(Integer page, Integer rows);

    /**
     * 根据商品类目的id，获得该商品类目的规格参数模板对象
     * @param cid 商品类目(itemCat)的id   1：1
     */
    public EgoResult loadTbItemParamByCid(Long cid);

    /**
     * 新增商品规格参数模板
     * @param cid       商品类目id
     * @param paramData 商品规格参数模板数据
     * @return
     */
    public EgoResult saveItemParam(Long cid, String paramData);

    /**
     * 批量删除商品规格参数模板
     * @param ids 前端传来的商品规格参数模板id数组
     */
    public EgoResult deleteItemParam(Long[] ids);
}
