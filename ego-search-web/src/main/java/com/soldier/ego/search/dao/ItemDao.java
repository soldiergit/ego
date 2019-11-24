package com.soldier.ego.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-23上午9:08
 * @Describe:完成商品基本信息的检索
 **/
public interface ItemDao {

    /**
     * 完成商品索引数据的检索
     * @param params    封装检索条件
     * @return          Response对象
     */
    public QueryResponse loadItem(SolrQuery params);
}
