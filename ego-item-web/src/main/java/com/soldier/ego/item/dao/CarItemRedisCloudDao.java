package com.soldier.ego.item.dao;

import java.util.Map;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25上午8:24
 * @Describe:购物车访问redis集群数据库的方法
 *      将商品信息和数量用商品id作为key保存到购物车Map，将购物车Map用用户id作为key保存到redis数据库
 **/
public interface CarItemRedisCloudDao {

    /**
     * 保存购物车数据到redis数据库
     * @param userId    用户id
     * @param carMap    购物车集合（k-商品id， v-购物车对象），因为redis保存的要么是字节数组，要么是字符串数组
     */
    public void saveCarMap(String userId, Map<String, String> carMap);

    /**
     * 查询用户购物车数据
     * @param userId    用户id
     */
    public Map<String, String> findCarMapByUserId(String userId);

    /**
     * 查询当前用户某个特定商品对应的购物车对象
     * @param userId    用户id
     * @param itemId    特定商品id
     */
    public String findCarItemByItemId(String userId, String itemId);
}
