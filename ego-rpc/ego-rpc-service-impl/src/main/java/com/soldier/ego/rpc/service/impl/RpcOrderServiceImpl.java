package com.soldier.ego.rpc.service.impl;

import com.soldier.ego.rpc.mapper.TbOrderItemMapper;
import com.soldier.ego.rpc.mapper.TbOrderMapper;
import com.soldier.ego.rpc.mapper.TbOrderShippingMapper;
import com.soldier.ego.rpc.pojo.*;
import com.soldier.ego.rpc.service.RpcOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-26下午7:08
 * @Describe:订单接口
 **/
@Service
public class RpcOrderServiceImpl implements RpcOrderService {

    @Autowired
    private TbOrderMapper orderMapper;
    @Autowired
    private TbOrderItemMapper orderItemMapper;
    @Autowired
    private TbOrderShippingMapper orderShippingMapper;

    @Override
    public void saveOrderService(TbOrder order, List<TbOrderItem> orderItems, TbOrderShipping orderShipping) {
        try {
            orderMapper.insert(order);

            for (TbOrderItem orderItem : orderItems) {
                orderItemMapper.insert(orderItem);
            }
            orderShippingMapper.insert(orderShipping);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TbOrder> findOrderListByUserIdService(Long userId) {

        TbOrderExample example = new TbOrderExample();
        TbOrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);

        return orderMapper.selectByExample(example);
    }

    @Override
    public List<TbOrderItem> findOrderItemListByOrderIdService(String orderId) {

        TbOrderItemExample example = new TbOrderItemExample();
        TbOrderItemExample.Criteria criteria = example.createCriteria();
        criteria.andOrderIdEqualTo(orderId);

        return orderItemMapper.selectByExample(example);
    }
}
