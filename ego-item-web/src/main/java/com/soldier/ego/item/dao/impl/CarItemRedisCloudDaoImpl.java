package com.soldier.ego.item.dao.impl;

import com.soldier.ego.item.dao.CarItemRedisCloudDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

import java.util.Map;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25下午1:21
 * @Describe:购物车访问redis集群数据库的方法
 *      将商品信息和数量用商品id作为key保存到购物车Map，将购物车Map用用户id作为key保存到redis数据库
 **/
@Repository
public class CarItemRedisCloudDaoImpl implements CarItemRedisCloudDao {

    // 注入JedisCluster集群访问对象
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public void saveCarMap(String userId, Map<String, String> carMap) {
        //  使用jedis保存集合的方法保存
        jedisCluster.hmset(userId, carMap);
    }

    @Override
    public Map<String, String> findCarMapByUserId(String userId) {
        //  查询某个key的map集合
        return jedisCluster.hgetAll(userId);
    }

    @Override
    public String findCarItemByItemId(String userId, String itemId) {
        //  查询某个key的map集合中某个字key的值
        return jedisCluster.hget(userId, itemId);
    }
}
