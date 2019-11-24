package com.soldier.ego.search.service.impl;

import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.rpc.service.ItemService;
import com.soldier.ego.search.dao.ItemDao;
import com.soldier.ego.search.entity.Item;
import com.soldier.ego.search.entity.SearchResult;
import com.soldier.ego.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-23上午9:22
 * @Describe:
 **/
@Service
public class SearchItemServiceImpl implements SearchItemService {

    //  注入dao对象
    @Autowired
    private ItemDao itemDao;

    //注入的是远程服务的代理对象
    @Autowired
    private ItemService itemServiceProxy;

    @Override
    public SearchResult loadItemService(String item_keywords, Integer page) {

        /**
         * 封装索引库查询条件
         */
        SolrQuery params = new SolrQuery();

        //  设置默认查询条件
        params.set("df", "item_keywords");

        //设置查询条件
        if (!StringUtils.isEmpty(item_keywords)) {
            params.setQuery(item_keywords);
        } else {
            //  查询全部
            params.set("q", "*:*");
        }

        //指定分页参数
        Integer rows = 20;
        //  进行最小页判断
        if (page < 1) page = 1;
        //  进行最大页判断
        Integer totalPages = 100;
        if (page > totalPages) page = totalPages;
        Integer start = (page - 1) * rows;
        params.setStart(start);
        params.setRows(rows);


        /**
         * 调用dao方法查询
         */
        QueryResponse response = itemDao.loadItem(params);

        //  设定高亮参数
        params.setHighlight(true);
        params.addHighlightField("title");
        params.setHighlightSimplePost("<font color='red'>");    //设置高亮前缀
        params.setHighlightSimplePost("</font>");               //设置高亮后缀

        //  获取本次查询到的文档集合
        SolrDocumentList docList = response.getResults();

        //  获取本次查询的高亮数据
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        //  获取本次查询的总记录数
        long total = docList.getNumFound();

        //  将docList转为List<Item>
        DocumentObjectBinder binder = new DocumentObjectBinder();
        List<Item> itemList = binder.getBeans(Item.class, docList);

        //  设置高亮数据
        for (Item item : itemList) {
            String id = item.getId();
            //  获得某个商品信息的高亮数据
            Map<String, List<String>> map = highlighting.get(id);
            //  获得某个商品的某个字段的高亮数据
            List<String> titles = map.get("title");
            if (titles != null && titles.size() > 0) item.setTitle(titles.get(0));
        }

        //  封装
        SearchResult result = new SearchResult();
        result.setTotalPages(Long.parseLong(totalPages+""));
        result.setTotal(total);
        result.setItemList(itemList);

        return result;
    }

    @Override
    public TbItem loadItemService(Long id) {
        return itemServiceProxy.selectItemById(id);
    }
}
