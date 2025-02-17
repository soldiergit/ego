package com.soldier.ego.manager.service;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.TreeNode;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-4下午4:11
 * @Describe:商品类目
 **/
public interface ManagerItemCatService {

    /**
     * 根据某个节点的id，查询对应的子节点集合
     * @param id 节点id
     * @return
     */
    public List<TreeNode> getItemCatList(Long id);

    /**
     * 根据id，查询商品类目
     * @param id 商品类目id
     */
    public EgoResult loadItemCatById(Long id);
}
