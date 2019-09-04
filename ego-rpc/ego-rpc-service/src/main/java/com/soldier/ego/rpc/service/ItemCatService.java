package com.soldier.ego.rpc.service;

import com.soldier.ego.rpc.pojo.TbItemCat;

import java.util.List;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-9-4 下午3:56
 * @Describe: 商品类目接口
 **/
public interface ItemCatService {

    /**
     * 根据某个节点的id，查询对应的子节点集合
     * @param id 节点id
     * @return
     */
    public List<TbItemCat> getItemCatListByParentId(Long id);
}
