package com.soldier.ego.search.service.impl;

import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.rpc.service.ItemService;
import com.soldier.ego.search.service.ImportItemService;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25上午9:04
 * @Describe:
 **/
@Service
public class ImportItemServiceImpl implements ImportItemService {

    @Autowired
    private ItemService itemServiceProxy;
    @Autowired
    private CloudSolrServer cloudServer;

    @Override
    public void importItemService() {
        try {
            //查询数据库中的数据
            List<TbItem> list = itemServiceProxy.selectAll();
            List<SolrInputDocument> result = new ArrayList<>();

            //模型转换
            for (TbItem item : list) {
                SolrInputDocument docu = new SolrInputDocument();
                docu.setField("id", String.valueOf(item.getId()));
                docu.setField("item_title", item.getTitle());
                docu.setField("item_sell_point", item.getSellPoint());
                docu.setField("item_price", item.getPrice());
                docu.setField("item_image", item.getImage());
                result.add(docu);
            }
            this.cloudServer.add(result);
            this.cloudServer.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
