package com.soldier.ego.sso.controller;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.rpc.pojo.TbUser;
import com.soldier.ego.sso.service.SsoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-24上午10:01
 * @Describe:sso系统用户验证
 **/
@Controller
public class SsoUserController {

    @Autowired
    private SsoUserService ssoUserService;

    /**
     * 处理用户名唯一性验证请求
     * @param param     验证数据
     * @param type      验证类型
     * @param callback  回到函数
     *      MappingJacksonValue --> 返回json，支持JSONP（其实是解决JS跨域调用数据的一种方案）
     *      required = false --> 非必须
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public MappingJacksonValue checkUser(@PathVariable String param, @PathVariable Integer type,
                                        @RequestParam(required = false) String callback) {
        EgoResult result = ssoUserService.loadUserByCond(param, type);

        //  处理json响应数据格式
        MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
        if (!StringUtils.isEmpty(callback)) jacksonValue.setJsonpFunction(callback);

        return jacksonValue;
    }

    /**
     * 实现用户注册
     * @param user  用户信息
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public EgoResult insertUser(TbUser user) {
        return ssoUserService.insertUser(user);
    }

    /**
     * 实现用户登录
     * @param username  用户名
     * @param password  密码
     * @param request   request对象是服务器对浏览器请求的封装
     * @param response  response是服务器对服务器响应的封装
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public EgoResult userLogin(String username, String password,
                               HttpServletRequest request, HttpServletResponse response) {
        return ssoUserService.userLogin(username, password, request, response);
    }

    /**
     * 处理获得用户登录状态请求
     * @param token     用户登录凭证
     * @param callback  回到函数
     *      MappingJacksonValue --> 返回json，支持JSONP（其实是解决JS跨域调用数据的一种方案）
     *      required = false --> 非必须
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping("/user/token/{token}")
    @ResponseBody
    public MappingJacksonValue userToken(@PathVariable String token, @RequestParam(required = false) String callback) {
        EgoResult result = ssoUserService.loadUserByToken(token);

        //  处理json响应数据格式
        MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
        if (!StringUtils.isEmpty(callback)) jacksonValue.setJsonpFunction(callback);

        return jacksonValue;
    }

    /**
     * 处理用户退出登录的请求
     * @param token     用户登录凭证
     * @param callback  回到函数
     *      MappingJacksonValue --> 返回json，支持JSONP（其实是解决JS跨域调用数据的一种方案）
     *      required = false --> 非必须
     *      @ResponseBody 异步的，不会进行跳转
     */
    @RequestMapping("/user/logout/{token}")
    @ResponseBody
    public MappingJacksonValue userLogout(@PathVariable String token, @RequestParam(required = false) String callback) {
        EgoResult result = ssoUserService.deleteUserByToken(token);

        //  处理json响应数据格式
        MappingJacksonValue jacksonValue = new MappingJacksonValue(result);
        if (!StringUtils.isEmpty(callback)) jacksonValue.setJsonpFunction(callback);

        return jacksonValue;
    }
}
