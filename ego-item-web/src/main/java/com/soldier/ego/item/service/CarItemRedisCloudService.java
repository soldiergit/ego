package com.soldier.ego.item.service;

import com.soldier.ego.item.entity.CarItem;

import java.util.Map;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25上午8:24
 * @Describe:将商品信息和数量用商品id作为key保存到购物车Map，将购物车Map用用户id作为key保存到redis数据库
 **/
public interface CarItemRedisCloudService {

    /**
     * 将商品放入购物车
     * @param itemId    商品id
     * @param userId    用户id
     */
    public void addItemToCarService(Long itemId, Long userId);

    /**
     * 查询userId用户购物车map集合
     * @param userId
     */
    public Map<Long, CarItem> findCarMapByUserIdService(Long userId);

    /**
     * 修改当前用户购物车集合某个特定商品对应的购物车对象的数量
     * @param itemId    商品id
     * @param userId    用户id
     * @param num       要改的商品数量
     */
    public String updateCarItemNumService(Long itemId, Long userId, Integer num);

    /**
     * 删除userId用户购物车map集合中itemId对应的购物车对象
     * @param itemId    商品id
     * @param userId    用户id
     */
    public void deleteCarItemService(Long itemId, Long userId);

    /**
     * 批量删除userId用户购物车map集合中itemId对应的购物车对象
     * @param itemIds   商品id集合
     * @param userId    用户id
     */
    public String deleteBatchCarItemService(String itemIds, Long userId);

    /**
     * 清空用户购物车
     * @param userId    用户id
     */
    public void deleteAllCarItemService(Long userId);
}
