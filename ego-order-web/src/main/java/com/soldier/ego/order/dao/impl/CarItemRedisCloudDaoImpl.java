package com.soldier.ego.order.dao.impl;

import com.soldier.ego.order.dao.CarItemRedisCloudDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import java.util.Map;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-26上午10:07
 * @Describe:购物车访问redis集群数据库的方法
 *      将商品信息和数量用商品id作为key保存到购物车Map，将购物车Map用用户id作为key保存到redis数据库
 **/
@Repository
public class CarItemRedisCloudDaoImpl implements CarItemRedisCloudDao {

    // 注入JedisCluster集群访问对象
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public Map<String, String> findCarMapByUserId(String userId) {
        //  查询userId用户购物车map集合
        return jedisCluster.hgetAll(userId);
    }

    @Override
    public void deleteCarMapAll(String userId) {
        //  直接删除购物车Map的key
        jedisCluster.del(userId);
    }
}
