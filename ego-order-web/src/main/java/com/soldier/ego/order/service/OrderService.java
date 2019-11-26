package com.soldier.ego.order.service;

import com.soldier.ego.order.entity.CarItem;
import com.soldier.ego.rpc.pojo.TbOrder;
import com.soldier.ego.rpc.pojo.TbOrderItem;
import com.soldier.ego.rpc.pojo.TbOrderShipping;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25上午8:24
 * @Describe:将商品信息和数量用商品id作为key保存到购物车Map，将购物车Map用用户id作为key保存到redis数据库
 **/
public interface OrderService {

    /**
     * 加载用户购物车map集合
     * @param userId
     */
    public Map<Long, CarItem> findCarMapService(Long userId);

    /**
     * 用户下单
     * @param order         订单信息
     * @param userId        用户id
     * @param orderShipping 物流配送信息
     */
    public Map<String, String> saveOrder(TbOrder order, Long userId, TbOrderShipping orderShipping);

    /**
     * 订单列表
     * @param userId    用户id
     */
    public List<TbOrder> findOrderListByUserId(Long userId);

    /**
     * 订单明细
     * @param orderId   订单id
     */
    public List<TbOrderItem> findOrderItemListByOrderId(String orderId);

}
