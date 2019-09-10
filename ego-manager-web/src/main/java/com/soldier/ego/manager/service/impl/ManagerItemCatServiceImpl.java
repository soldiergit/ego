package com.soldier.ego.manager.service.impl;

import com.soldier.ego.beans.TreeNode;
import com.soldier.ego.manager.service.ManagerItemCatService;
import com.soldier.ego.rpc.pojo.TbItemCat;
import com.soldier.ego.rpc.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-4下午4:13
 * @Describe:商品类目
 **/
@Service
public class ManagerItemCatServiceImpl implements ManagerItemCatService {

    //注入的是远程服务的代理对象
    @Autowired
    private ItemCatService itemCatServiceProxy;

    @Override
    public List<TreeNode> getItemCatList(Long id) {

        //调用远程服务
        List<TbItemCat> itemCatList = itemCatServiceProxy.getItemCatListByParentId(id);

        //创建List<TreeNode>集合对象
        List<TreeNode> nodeList = new ArrayList<TreeNode>();

        TreeNode node = null;
        for (TbItemCat cat : itemCatList) {
            node = new TreeNode();
            node.setId(cat.getId());
            node.setText(cat.getName());
            node.setState(cat.getIsParent() ? "closed" : "open");
            nodeList.add(node);
        }

        return nodeList;
    }
}
