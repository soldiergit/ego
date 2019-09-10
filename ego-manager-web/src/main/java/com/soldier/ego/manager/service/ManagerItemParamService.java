package com.soldier.ego.manager.service;

import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.pojo.TbItemParam;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-10上午9:45
 * @Describe:商品规格参数模板
 **/
public interface ManagerItemParamService {

    /**
     * 商品规格参数模板的分页显示
     */
    public PageResult<TbItemParam> loadTbItemParamList(Integer page, Integer rows);
}
