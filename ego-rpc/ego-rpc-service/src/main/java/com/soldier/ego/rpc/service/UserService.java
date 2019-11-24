package com.soldier.ego.rpc.service;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.rpc.pojo.TbUser;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-24上午10:15
 * @Describe:系统用户接口
 **/
public interface UserService {

    /**
     * 实现用户名唯一性验证
     * @param cond  验证数据
     * @param type  验证类型
     */
    public EgoResult selectUserByCondService(String cond, Integer type);

    /**
     * 实现用户注册
     * @param user  用户信息
     */
    public EgoResult insertUserService(TbUser user);

    /**
     * 实现用户登录
     * @param username  用户名
     */
    public TbUser selectUserByUserNameService(String username);
}
