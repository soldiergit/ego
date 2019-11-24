package com.soldier.ego.rpc.service.impl;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.rpc.mapper.TbUserMapper;
import com.soldier.ego.rpc.pojo.TbUser;
import com.soldier.ego.rpc.pojo.TbUserExample;
import com.soldier.ego.rpc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-24上午10:18
 * @Describe:系统用户接口
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public EgoResult selectUserByCondService(String cond, Integer type) {

        //动态产生where条件
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();

        //封装查询条件
        if (type.equals(1)) {
            criteria.andUsernameEqualTo(cond);
        } else if (type.equals(2)) {
            criteria.andPhoneEqualTo(cond);
        } else if (type.equals(3)) {
            criteria.andEmailEqualTo(cond);
        }

        // where username=?...
        List<TbUser> userList = tbUserMapper.selectByExample(example);

        //  创建EgoResult对象
        EgoResult result = new EgoResult();
        result.setStatus(200);
        result.setMsg("ok");
        if (userList!=null && userList.size()>0) result.setData(false);
        else result.setData(true);  //  用户名可用

        return result;
    }

    @Override
    public EgoResult insertUserService(TbUser user) {

        EgoResult result = new EgoResult();

        try {
            Date date = new Date();
            user.setCreated(date);
            user.setUpdated(date);
            tbUserMapper.insert(user);
            result.setStatus(200);
            result.setMsg("注册成功.");
        } catch (Exception e) {
            result.setStatus(400);
            result.setMsg("注册失败. 请校验数据后请再提交数据.");
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public TbUser selectUserByUserNameService(String username) {

        //动态产生where条件
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();

        //封装查询条件
        criteria.andUsernameEqualTo(username);

        // where username=?
        List<TbUser> userList = tbUserMapper.selectByExample(example);

        //  因为用户名唯一
        if (userList!=null && userList.size()==1) return userList.get(0);

        return null;
    }
}
