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
     * 查询用户的购物车集合
     * @param userId
     */
    public Map<Long, CarItem> findCarListByUserIdService(Long userId);
}
