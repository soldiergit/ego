package com.soldier.ego.portal.service.impl;

import com.soldier.ego.beans.CatNode;
import com.soldier.ego.beans.CatResult;
import com.soldier.ego.beans.JsonUtils;
import com.soldier.ego.portal.service.PortalItemCatService;
import com.soldier.ego.rpc.pojo.TbItemCat;
import com.soldier.ego.rpc.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-10-4下午6:31
 * @Describe:网站门户-商品类目接口
 **/
@Service
public class PortalItemCatServiceImpl implements PortalItemCatService {

    // 加载配置文件的值，根据key；作为redis缓存数据库的key
    @Value("${ITEM_CAT}")
    private String itemCatKey;
    // 注入JedisCluster集群访问对象
    @Autowired
    private JedisCluster jedisCluster;

    //注入的是远程服务的代理对象
    @Autowired
    private ItemCatService itemCatServiceProxy;

    @Override
    public String loadItemCatList() {

        // 查询集群中是否存在值
        String jsonStr = jedisCluster.get(itemCatKey);
        if (!StringUtils.isEmpty(jsonStr)) {
            return jsonStr;
        }

        List<TbItemCat> itemCatList = itemCatServiceProxy.loadItemCatListService();

        // 创建CatResult对象
        CatResult result = new CatResult();
        // 将List转化为符合前端规范的数据格式，详情请看《商品分类数据格式.txt》；递归遍历list
        List<?> data = getChildren(0L, itemCatList);
        result.setData(data);

        //将result对象序列化成json字符串
        String str = JsonUtils.objectToJson(result);

        // 将str缓存到redis集群中
        jedisCluster.set(itemCatKey, str);

        return str;
    }

    /**
     * 递归遍历List
     * @param parentId      父类目id
     * @param itemCatList   数据
     * @return
     */
    private List<?> getChildren(Long parentId, List<TbItemCat> itemCatList) {
        // 盛放指定分类下的所有子分类信息
        List resultList = new ArrayList();

        for (TbItemCat itemCat : itemCatList) {

            if (itemCat.getParentId().equals(parentId)) {
                if (itemCat.getIsParent()) {
                    // 如果itemCat代表一级分类或者二级分类

                    CatNode catNode = new CatNode();

                    if (itemCat.getParentId().longValue() == 0) {
                        // 如果是一级分类，格式："<a href='/products/1.html'>图书、音像、电子书</a>",
                        catNode.setName(
                                "<a href='/products/" + itemCat.getId() + ".html'>" + itemCat.getName() + "</a>");
                    } else {
                        // 如果是一级分类，格式："电子书刊"
                        catNode.setName(itemCat.getName());
                    }
                    // "/products/2.html",
                    catNode.setUrl("/products/" + itemCat.getId() + ".html");
                    catNode.setList(getChildren(itemCat.getId(), itemCatList));
                    // 将节点添加到list集合中
                    resultList.add(catNode);
                } else {
                    // 如果itemCat代表是三级分类，格式："/products/3.html|电子书",
                    resultList.add("/products/" + itemCat.getId() + ".html|" + itemCat.getName());
                }
            }
        }
        return resultList;
    }
}
