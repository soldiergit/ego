package com.soldier.ego.item.service.impl;

import com.soldier.ego.beans.JsonUtils;
import com.soldier.ego.item.dao.CarItemRedisCloudDao;
import com.soldier.ego.item.entity.CarItem;
import com.soldier.ego.item.service.CarItemRedisCloudService;
import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25下午1:26
 * @Describe:将商品信息和数量用商品id作为key保存到购物车Map，将购物车Map用用户id作为key保存到redis数据库
 **/
@Service
public class CarItemRedisCloudServiceImpl implements CarItemRedisCloudService {

    //  注入dao对象
    @Autowired
    private CarItemRedisCloudDao carItemRedisCloudDao;

    //注入的是远程服务的代理对象
    @Autowired
    private ItemService itemServiceProxy;

    @Override
    public void addItemToCarService(Long itemId, Long userId) {
        Map<String, String> carMap = null;
        CarItem carItem = null;

        //  获得商品对象
        TbItem tbItem = itemServiceProxy.selectItemById(itemId);

        /**
         * 判断用户是否第一次购物，即判断是否存在当前用户的carMap集合
         */
        carMap = carItemRedisCloudDao.findCarMapByUserId(String.valueOf(userId));
        if (carMap == null) {
            //  创建Map集合对象--因为redis保存的要么是字节数组，要么是字符串数组
            carMap = new HashMap<>();
        }

        /**
         * 判断该用户购买的特定商品在其购物车集合中是否存在
         */
        String carItemByItemId = carItemRedisCloudDao.findCarItemByItemId(String.valueOf(userId), String.valueOf(itemId));
        if (StringUtils.isEmpty(carItemByItemId)) {
            //  创建该商品的购物车对象
            carItem = new CarItem();
            //  将商品信息放入购物车
            carItem.setItem(tbItem);
            //  数量设为1
            carItem.setNum(1);
        } else {
            //  反序列化CarItem
            carItem = JsonUtils.jsonToPojo(carItemByItemId, CarItem.class);
            //  数量+1
            carItem.setNum(carItem.getNum()+1);
        }

        //  将购物车对象转为JSON对象
        String jsonCarStr = JsonUtils.objectToJson(carItem);
        //  第一步：用 商品id作为key保存到购物车Map
        carMap.put(String.valueOf(itemId), jsonCarStr);

        //  第二步：用 用户id作为key保存到redis数据库
        carItemRedisCloudDao.saveCarMap(String.valueOf(userId), carMap);
    }

    @Override
    public Map<Long, CarItem> findCarMapByUserIdService(Long userId) {

        Map<Long, CarItem> carMap = new HashMap<>();

        //  查询某个用户的购物车列表
        Map<String, String> carMapByUserId = carItemRedisCloudDao.findCarMapByUserId(String.valueOf(userId));
        for (Map.Entry<String, String> entry : carMapByUserId.entrySet()) {
            String key = entry.getKey();        //商品id
            String value = entry.getValue();    //购物车
            CarItem carItem = JsonUtils.jsonToPojo(value, CarItem.class);

            carMap.put(Long.parseLong(key), carItem);
        }

        return carMap;
    }

    @Override
    public String updateCarItemNumService(Long itemId, Long userId, Integer num) {
        try {
            //  从该用户的购物车集合中获得需要修改商品的购物车对象
            String carItemByItemId = carItemRedisCloudDao.findCarItemByItemId(String.valueOf(userId), String.valueOf(itemId));

            //  将carItemByItemId反序列化为CarItem对象
            CarItem carItem = JsonUtils.jsonToPojo(carItemByItemId, CarItem.class);
            carItem.setNum(num);

            //  将carItem修改后数据更新到redis数据库
            carItemRedisCloudDao.updateCarMapNum(String.valueOf(userId), String.valueOf(itemId), JsonUtils.objectToJson(carItem));

            return "ok";
        } catch (Exception e) {
            System.out.println("####更新CarItemNum失败####");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteCarItemService(Long itemId, Long userId) {
        carItemRedisCloudDao.deleteCarMapItem(String.valueOf(userId), String.valueOf(itemId));
    }

    @Override
    public String deleteBatchCarItemService(String itemIds, Long userId) {
        try {
            for (String itemId : itemIds.split(",")) {
                carItemRedisCloudDao.deleteCarMapItem(String.valueOf(userId), itemId);
            }
            return "ok";
        } catch (Exception e) {
            System.out.println("####批量删除CarItemNum失败####");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAllCarItemService(Long userId) {
        carItemRedisCloudDao.deleteCarMapAll(String.valueOf(userId));
    }
}
