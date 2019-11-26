package com.soldier.ego.sso.service.impl;

import com.soldier.ego.beans.CookieUtils;
import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.JsonUtils;
import com.soldier.ego.rpc.pojo.TbUser;
import com.soldier.ego.rpc.service.UserService;
import com.soldier.ego.sso.service.SsoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-24上午10:49
 * @Describe:
 **/
@Service
public class SsoUserServiceImpl implements SsoUserService {

    //注入的是远程服务的代理对象
    @Autowired
    private UserService userServiceProxy;

    // 注入JedisCluster集群访问对象
    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public EgoResult loadUserByCond(String cond, Integer type) {
        return userServiceProxy.selectUserByCondService(cond, type);
    }

    @Override
    public EgoResult insertUser(TbUser user) {
        //  md5加密
        String pwd = user.getPassword();
        String md5 = DigestUtils.md5DigestAsHex(pwd.getBytes());
        user.setPassword(md5);
        return userServiceProxy.insertUserService(user);
    }

    @Override
    public EgoResult userLogin(String username, String password,
                               HttpServletRequest request, HttpServletResponse response) {

        EgoResult result = new EgoResult();
        result.setStatus(400);
        result.setData(null);
        result.setMsg("用户名或密码错误.");
        TbUser tbUser = userServiceProxy.selectUserByUserNameService(username);

        if (tbUser != null) {
            //对前端提交的密码进行加密
            password = DigestUtils.md5DigestAsHex(password.getBytes());
            if (password.equals(tbUser.getPassword())) {
                //  将当前登录用户对象，转为json字符串，保存到redis数据库
                String userJsonStr = JsonUtils.objectToJson(tbUser);
                String token = UUID.randomUUID().toString();
                // 将用户信息保存到redis数据库
                jedisCluster.set(token, userJsonStr);
                //  设置token的有效期为3分钟
                jedisCluster.expire(token, 1800);

                /**
                 * 将token响应到客户端
                 */
                CookieUtils.setCookie(request,response, "SSO_TOKEN", token);

                result.setStatus(200);
                result.setMsg("登录成功.");
                result.setData(token);
            }
        }

        return result;
    }

    @Override
    public EgoResult loadUserByToken(String token) {

        EgoResult result = new EgoResult();
        result.setStatus(400);
        result.setMsg("error");
        result.setData(null);

        String userJsonStr = jedisCluster.get(token);

        if (!StringUtils.isEmpty(userJsonStr)) {
            result.setStatus(200);
            result.setMsg("ok");
//            result.setData(userJsonStr);

            TbUser tbUser = JsonUtils.jsonToPojo(userJsonStr, TbUser.class);
            result.setData(tbUser);
        }

        return result;
    }

    @Override
    public EgoResult deleteUserByToken(String token) {

        EgoResult result = new EgoResult();
        result.setStatus(400);
        result.setMsg("error");
        result.setData(null);

        //  删除redis数据
        Long del = jedisCluster.del(token);

        if (!del.equals(0L)) {
            result.setStatus(200);
            result.setMsg("ok");
            result.setData("");
        }

        return result;
    }
}
