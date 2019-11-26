package com.soldier.ego.item.controller;

import com.soldier.ego.beans.JsonUtils;
import com.soldier.ego.item.entity.CarItem;
import com.soldier.ego.item.service.CarItemRedisCloudService;
import com.soldier.ego.rpc.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25上午8:56
 * @Describe:购物车
 **/
@Controller
public class CartItemController {

    @Autowired
    private CarItemRedisCloudService carItemRedisCloudService;

    /**
     * 处理将商品添加到购物车的请求
     * @param itemId    商品id
     * @param request   用于获取用户信息
     */
    @RequestMapping("/cart/add/{itemId}")
    public String carAdd(@PathVariable Long itemId,
                          HttpServletRequest request) {

        //  用拦截器中反序列化获得user对象
        TbUser tbUser = (TbUser) request.getAttribute("user");

        carItemRedisCloudService.addItemToCarService(itemId, tbUser.getId());

        return "cartSuccess";
    }

    /**
     * 处理加载用户购物车的请求
     * @param request   用于获取用户信息
     */
    @RequestMapping("/cart/cart")
    public String getCar(HttpServletRequest request) {

        //  用拦截器中反序列化获得user对象
        TbUser tbUser = (TbUser) request.getAttribute("user");
        Map<Long, CarItem> carMap = carItemRedisCloudService.findCarMapByUserIdService(tbUser.getId());

        request.setAttribute("carMap", carMap);

        return "cart";
    }

    /**
     * 处理更新购物车数量请求
     */
    @RequestMapping("/cart/update/num/{itemId}/{num}")
    @ResponseBody
    public String carUpdateNum(@PathVariable Long itemId, @PathVariable Integer num,
                               HttpServletRequest request) {

        //  用拦截器中反序列化获得user对象
        TbUser tbUser = (TbUser) request.getAttribute("user");

        return carItemRedisCloudService.updateCarItemNumService(itemId, tbUser.getId(), num);
    }

    /**
     * 处理更新购物车数量请求
     */
    @RequestMapping("/cart/delete/{itemId}")
    public String deleteCarItem(@PathVariable Long itemId, HttpServletRequest request) {

        //  用拦截器中反序列化获得user对象
        TbUser tbUser = (TbUser) request.getAttribute("user");

        carItemRedisCloudService.deleteCarItemService(itemId, tbUser.getId());

        //  重定向到/cart/cart请求，重新加载购物车列表
        return "redirect:/cart/cart.html";
    }

    /**
     * 批量删除购物车数量请求
     */
    @RequestMapping("/cart/delete/batch/{itemIds}")
    public String deleteBatchCarItem(@PathVariable String itemIds, HttpServletRequest request) {

        //  用拦截器中反序列化获得user对象
        TbUser tbUser = (TbUser) request.getAttribute("user");

        return carItemRedisCloudService.deleteBatchCarItemService(itemIds, tbUser.getId());
    }

    /**
     * 批量删除购物车数量请求
     */
    @RequestMapping("/cart/delete/all")
    public String deleteAllCarItem(HttpServletRequest request) {

        //  用拦截器中反序列化获得user对象
        TbUser tbUser = (TbUser) request.getAttribute("user");

        carItemRedisCloudService.deleteAllCarItemService(tbUser.getId());

        //  重定向到/cart/cart请求，重新加载购物车列表
        return "redirect:/cart/cart.html";
    }
}
