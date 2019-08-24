package com.soldier.ego.rpc.service;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.pojo.TbItem;

import java.util.List;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-22 下午4:34
 * @Describe:
 **/
public interface ItemService {

    /**
     * 实现商品信息的分页查询
     */
    public PageResult<TbItem> selectItemList(Integer page, Integer rows);

    /**
     * 完成商品上下架状态的修改
     * @param itemIds 商品的idj集合
     * @param flag true:上架,false:下架
     */
    public EgoResult updateItemStatus(List<Long> itemIds, Boolean flag);

    /**
     * 删除商品信息
     * @param itemIds  需要删除的商品信息的id集合
     */
    public EgoResult deleteItem(List<Long> itemIds);
}
