package com.soldier.ego.rpc.service;

import com.soldier.ego.beans.PageResult;
import com.soldier.ego.rpc.pojo.TbItemParam;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-9-10上午9:17
 * @Describe:商品规格参数模板接口
 *  方案一：分表存储
 *
 *  方案二：模板存储
 *  由于所有商品的规格参数信息格式都是一样的，可以直接使用 json 的形式，将 json 串保存
 * 到数据库。
 * 商品规格分组
 * |----规格项:规格值
 * |----规格项:规格值
 * |----规格项:规格值
 * 商品规格分组
 * |----规格项:规格值
 * |----规格项:规格值
 * |----规格项:规格值
 *
 *
 * 方案一存在问题:
 * 需要创建多张表来描述规格参数之间的关系。
 * 查询时需要复杂的 sql 语句查询。
 * 规格参数数据量是商品信息的几十倍，数据量十分庞大。查询时效率很低
 *
 * 方案二好处：
 * 不需要做表之间复杂的关系。
 * 如果要求新添加的商品规格项发生改变，之前的商品不变是很简单的。只需要改变 json 串
 * 的格式
 **/
public interface ItemParamService {

    /**
     * 商品规格参数模板的分页显示
     */
    public PageResult<TbItemParam> loadTbItemParamListService(Integer page, Integer rows);
}
