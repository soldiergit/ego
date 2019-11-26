package com.soldier.ego.order.service.impl;

import com.soldier.ego.beans.IDUtils;
import com.soldier.ego.beans.JsonUtils;
import com.soldier.ego.order.dao.CarItemRedisCloudDao;
import com.soldier.ego.order.entity.CarItem;
import com.soldier.ego.order.service.OrderService;
import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.rpc.pojo.TbOrder;
import com.soldier.ego.rpc.pojo.TbOrderItem;
import com.soldier.ego.rpc.pojo.TbOrderShipping;
import com.soldier.ego.rpc.service.RpcOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25下午1:26
 * @Describe:将商品信息和数量用商品id作为key保存到购物车Map，将购物车Map用用户id作为key保存到redis数据库
 **/
@Service
public class OrderServiceImpl implements OrderService {

    //  注入dao对象
    @Autowired
    private CarItemRedisCloudDao carItemRedisCloudDao;

    //注入的是远程服务的代理对象
    @Autowired
    private RpcOrderService rpcOrderServiceProxy;

    @Override
    public Map<Long, CarItem> findCarMapService(Long userId) {

        Map<Long, CarItem> carMap = new HashMap<>();

        //  查询某个用户的购物车列表
        Map<String, String> carMapByUserId = carItemRedisCloudDao.findCarMapByUserId(String.valueOf(userId));
        for (Map.Entry<String, String> entry : carMapByUserId.entrySet()) {

            carMap.put(Long.parseLong(entry.getKey()), JsonUtils.jsonToPojo(entry.getValue(), CarItem.class));
        }

        return carMap;
    }

    @Override
    public Map<String, String> saveOrder(TbOrder order, Long userId, TbOrderShipping orderShipping) {
        /**
         * 其它数据在前端获取了ordercart.jsp
         */
        try {

            Date date = new Date();
            String orderId = String.valueOf(IDUtils.genItemId());
            order.setOrderId(orderId);
            order.setPostFee("123");//邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
            order.setStatus(2);     //状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
            order.setCreateTime(date);
            order.setUpdateTime(date);
            order.setPaymentTime(date);
            order.setConsignTime(date);
            order.setEndTime(date);
            order.setCloseTime(date);
            order.setShippingName("EMS");   //物流名称
            order.setShippingCode("110110");    //物流单号
            order.setUserId(userId);
            order.setBuyerMessage("message");   //买家留言
            order.setBuyerNick("EMS");  //买家昵称
            order.setBuyerRate(0);  //买家是否已经评价

            //  获取用户的购物车集合
            Map<Long, CarItem> carMap = this.findCarMapService(userId);
            //  创建订单明细集合
            List<TbOrderItem> orderItems = new ArrayList<>();
            for (CarItem carItem : carMap.values()) {
                //  订单明细
                TbOrderItem orderItem = new TbOrderItem();
                String id = String.valueOf(IDUtils.genItemId());
                orderItem.setId(id);
                orderItem.setOrderId(orderId);
                //  购物车中的商品对象
                TbItem tbItem = carItem.getItem();
                orderItem.setItemId(String.valueOf(tbItem.getId()));
                orderItem.setNum(carItem.getNum());
                orderItem.setTitle(tbItem.getTitle());
                orderItem.setPrice(tbItem.getPrice());
                orderItem.setTotalFee(tbItem.getPrice() * carItem.getNum());    //小计
                orderItem.setPicPath(tbItem.getImages()[0]);
                //  放进list
                orderItems.add(orderItem);
            }

            orderShipping.setOrderId(orderId);
            orderShipping.setReceiverPhone("1110");
            orderShipping.setCreated(date);
            orderShipping.setUpdated(date);

            //  调用rpc业务方法
            rpcOrderServiceProxy.saveOrderService(order, orderItems, orderShipping);

            //  成功页面显示
            Map<String, String> map = new HashMap<>();
            map.put("orderId", orderId);
            map.put("total", order.getPayment());

            //  清空购物车
            carItemRedisCloudDao.deleteCarMapAll(String.valueOf(userId));
            return map;
        } catch (Exception e) {
            System.out.println("####提交订单失败！####");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TbOrder> findOrderListByUserId(Long userId) {
        return rpcOrderServiceProxy.findOrderListByUserIdService(userId);
    }

    @Override
    public List<TbOrderItem> findOrderItemListByOrderId(String orderId) {
        return rpcOrderServiceProxy.findOrderItemListByOrderIdService(orderId);
    }
}
