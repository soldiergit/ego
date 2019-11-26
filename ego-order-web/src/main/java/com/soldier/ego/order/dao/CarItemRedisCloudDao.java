package com.soldier.ego.order.dao;

import java.util.Map;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-26上午10:07
 * @Describe:购物车类
 *      将商品信息和数量用商品id作为key保存到购物车Map，将购物车Map用用户id作为key保存到redis数据库
 **/
public interface CarItemRedisCloudDao {

    /**
     * 加载用户购物车集合
     */
    public Map<String, String> findCarMapByUserId(String userId);

    /**
     * 清空用户购物车
     * @param userId    用户id
     */
    public void deleteCarMapAll(String userId);
}
