package com.soldier.ego.search.dao.impl;

import com.soldier.ego.search.dao.ItemSolrCloudDao;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-23上午9:11
 * @Describe:实现完成商品基本信息的检索
 **/
@Repository
public class ItemSolrCloudDaoImpl implements ItemSolrCloudDao {

    //  注入solr集群访问对象
    @Autowired
    private CloudSolrServer cloudSolrServer;

    @Override
    public QueryResponse loadItem(SolrQuery params) {
        try {
            return cloudSolrServer.query(params);
        } catch (SolrServerException e) {
            System.out.println("########查询索引库失败########");
            e.printStackTrace();
        }
        return null;
    }
}
