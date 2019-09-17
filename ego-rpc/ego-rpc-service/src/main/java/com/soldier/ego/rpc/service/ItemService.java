package com.soldier.ego.rpc.service;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.rpc.pojo.TbItemDesc;
import com.soldier.ego.rpc.pojo.TbItemParam;
import com.soldier.ego.rpc.pojo.TbItemParamItem;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-8-22下午4:34
 * @Describe:商品信息接口
 **/
public interface ItemService {

    /**
     * 实现商品信息的分页查询
     */
    public PageResult<TbItem> selectItemListService(Integer page, Integer rows);

    /**
     * 完成商品上下架状态的修改
     * @param itemIds   商品的idj集合
     * @param flag      true:上架,false:下架
     */
    public EgoResult updateItemStatusService(List<Long> itemIds, Boolean flag);

    /**
     * 删除商品信息
     * @param itemIds  需要删除的商品信息的id集合
     */
    public EgoResult deleteItemService(List<Long> itemIds);

    /**
     * 保存商品信息
     * @param item      商品基本信息
     * @param itemDesc  商品描述信息（富文本编辑器内容）
     * @param itemParamItem 商品规格参数信息
     */
    public EgoResult saveItemService(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem);

    /**
     * 更新商品信息
     * @param item      商品基本信息
     * @param itemDesc  商品描述信息（富文本编辑器内容）
     */
    public EgoResult updateItemService(TbItem item, TbItemDesc itemDesc, TbItemParamItem itemParamItem);
}
