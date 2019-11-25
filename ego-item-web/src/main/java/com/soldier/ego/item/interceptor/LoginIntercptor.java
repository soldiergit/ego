package com.soldier.ego.item.interceptor;

import com.soldier.ego.beans.CookieUtils;
import com.soldier.ego.beans.JsonUtils;
import com.soldier.ego.rpc.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25上午8:32
 * @Describe:
 *      拦截器的两种实现
 *          1、继承HandlerInterceptorAdapter
 *          2、实现接口HandlerInterceptor
 **/
public class LoginIntercptor extends HandlerInterceptorAdapter {

    // 注入JedisCluster集群访问对象
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        //  获取用户登录凭证-token
        String token = CookieUtils.getCookieValue(request, "SSO_TOKEN");

        if (!StringUtils.isEmpty(token)) {

            //  查询redis数据
            String jsonUserStr = jedisCluster.get(token);

            //  验证用户是否登录
            if (!StringUtils.isEmpty(jsonUserStr)) {

                TbUser tbUser = JsonUtils.jsonToPojo(jsonUserStr, TbUser.class);
                request.setAttribute("user", tbUser);

                //  说明登录，放行
                return true;
            }
        }

        //  获取用户登录成功后需要跳转的路径
        String url = request.getRequestURL().toString();
        //  重定向到登录页面，并将户登录成功后需要跳转的路径传过去（详情看login.jsp）
        response.sendRedirect("http://localhost:8083/login?redirect="+url);
        return false;
    }
}
