package com.soldier.ego.sso.service;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.rpc.pojo.TbUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-24上午9:55
 * @Describe:sso单点登录用户接口
 **/
public interface SsoUserService {

    /**
     * 实现用户名唯一性验证
     * @param cond  验证数据
     * @param type  验证类型
     */
    public EgoResult loadUserByCond(String cond, Integer type);

    /**
     * 实现用户注册
     * @param user  用户信息
     */
    public EgoResult insertUser(TbUser user);

    /**
     * 实现用户登录
     * @param username  用户名
     * @param password  密码
     * @param request   request对象是服务器对浏览器请求的封装
     * @param response  response是服务器对服务器响应的封装
     */
    public EgoResult userLogin(String username, String password,
                               HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据token获取用户登录状态
     * @param token 用户登录凭证
     */
    public EgoResult loadUserByToken(String token);

    /**
     * 实现噢ia用户状态的删除
     * @param token 用户登录凭证
     */
    public EgoResult deleteUserByToken(String token);

}
