package com.soldier.ego.rpc.service;

import com.soldier.ego.rpc.pojo.TbOrder;
import com.soldier.ego.rpc.pojo.TbOrderItem;
import com.soldier.ego.rpc.pojo.TbOrderShipping;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-26下午7:05
 * @Describe:订单接口
 **/
public interface RpcOrderService {

    /**
     * 完成订单信息的保存
     * @param order         订单对象
     * @param orderItems    订单名细集合
     * @param orderShipping 物流配送信息对象
     */
    public void saveOrderService(TbOrder order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping);

    /**
     * 订单列表
     * @param userId    用户id
     */
    public List<TbOrder> findOrderListByUserIdService(Long userId);

    /**
     * 订单明细
     * @param orderId   订单id
     */
    public List<TbOrderItem> findOrderItemListByOrderIdService(String orderId);
}
