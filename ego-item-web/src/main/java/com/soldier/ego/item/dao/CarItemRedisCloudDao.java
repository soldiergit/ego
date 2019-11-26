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
     * 新建、保存、覆盖userId用户购物车map集合到redis数据库
     * @param userId    用户id
     * @param carMap    购物车集合（k-商品id， v-购物车对象），因为redis保存的要么是字节数组，要么是字符串数组
     */
    public void saveCarMap(String userId, Map<String, String> carMap);

    /**
     * 查询userId用户购物车map集合
     * @param userId    用户id
     */
    public Map<String, String> findCarMapByUserId(String userId);

    /**
     * 查询userId用户购物车map集合中itemId对应的购物车对象
     * @param userId    用户id
     * @param itemId    特定商品id
     */
    public String findCarItemByItemId(String userId, String itemId);

    /**
     * 修改当前用户购物车集合某个特定商品对应的购物车对象
     * @param userId        用户id
     * @param itemId        特定商品id
     * @param carItemStr    序列化后的购物村对象
     */
    public void updateCarMapNum(String userId, String itemId, String carItemStr);

    /**
     * 删除userId用户购物车map集合中itemId对应的购物车对象
     * @param userId    用户id
     * @param itemId    特定商品id
     */
    public void deleteCarMapItem(String userId, String itemId);

    /**
     * 清空用户购物车
     * @param userId    用户id
     */
    public void deleteCarMapAll(String userId);
}
