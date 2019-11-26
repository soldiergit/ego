package com.soldier.ego.order.controller;

import com.soldier.ego.order.entity.CarItem;
import com.soldier.ego.order.service.OrderService;
import com.soldier.ego.rpc.pojo.TbOrder;
import com.soldier.ego.rpc.pojo.TbOrderItem;
import com.soldier.ego.rpc.pojo.TbOrderShipping;
import com.soldier.ego.rpc.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-26上午10:07
 * @Describe:
 **/
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 页面跳转
     * url为空时默认为index
     */
    @RequestMapping("/{url}")
    public String loadPage(@PathVariable String url) {
        return url;
    }

    /**
     * 跳转到订单确认页面
     */
    @RequestMapping("/order/cart")
    public String orderCart(HttpServletRequest request) {

        //  用拦截器中反序列化获得user对象
        TbUser tbUser = (TbUser) request.getAttribute("user");

        //  查询用户购物车
        Map<Long, CarItem> carMap = orderService.findCarMapService(tbUser.getId());
        request.setAttribute("carMap", carMap);

        return "ordercart";
    }

    /**
     * 新增订单
     */
    @RequestMapping("/order/save")
    public String orderSave(TbOrder order, TbOrderShipping orderShipping, HttpServletRequest request) {

        //  用拦截器中反序列化获得user对象
        TbUser tbUser = (TbUser) request.getAttribute("user");

        orderService.saveOrder(order, tbUser.getId(), orderShipping);

        return "redirect:http://localhost:8081";
    }

    /**
     * 订单列表
     */
    @RequestMapping("/order/list")
    public String orderList(HttpServletRequest request) {

        //  用拦截器中反序列化获得user对象
        TbUser tbUser = (TbUser) request.getAttribute("user");

        List<TbOrder> list = orderService.findOrderListByUserId(tbUser.getId());
        request.setAttribute("orderList", list);

        return "orders";
    }

    /**
     * 订单明细
     */
    @RequestMapping("/order/detail/list/{orderId}")
    public String orderDetail(@PathVariable String orderId, HttpServletRequest request) {

        List<TbOrderItem> list = orderService.findOrderItemListByOrderId(orderId);
        request.setAttribute("orderItemList", list);

        return "ordersdetail";
    }
}
