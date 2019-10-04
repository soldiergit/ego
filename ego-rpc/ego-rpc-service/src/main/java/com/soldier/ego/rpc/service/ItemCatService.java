package com.soldier.ego.rpc.service;

import com.soldier.ego.rpc.pojo.TbItemCat;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-4下午3:56
 * @Describe:商品类目接口
 **/
public interface ItemCatService {

    /**
     * 根据某个节点的id，查询对应的子节点集合
     * @param id 节点id
     * @return
     */
    public List<TbItemCat> getItemCatListByParentId(Long id);

    /**
     * 根据id，查询商品类目
     * @param id 商品类目id
     */
    public TbItemCat loadItemCatByIdService(Long id);

    /**
     * 网站门户-加载首页的商品类目
     */
    public List<TbItemCat> loadItemCatListService();
}
