package com.soldier.ego.search.service.impl;

import com.soldier.ego.beans.BigPicture;
import com.soldier.ego.beans.JsonUtils;
import com.soldier.ego.search.service.PortalContentService;
import com.soldier.ego.rpc.pojo.TbContent;
import com.soldier.ego.rpc.service.ContentService;
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
 * @create:19-10-4下午7:17
 * @Describe:网站门户-网站内容接口
 **/
@Service
public class PortalContentServiceImpl implements PortalContentService {

    // 加载配置文件的值，根据key；作为redis缓存数据库的key
    @Value("${CONTENT_PICTURE}")
    private String contentPictureKey;
    // 注入JedisCluster集群访问对象
    @Autowired
    private JedisCluster jedisCluster;

    //注入的是远程服务的代理对象
    @Autowired
    private ContentService contentServiceProxy;

    @Override
    public String loadContentListByCid(Long categoryId) {

        // 查询集群中是否存在值
        String jsonStr = jedisCluster.get(contentPictureKey);
        if (!StringUtils.isEmpty(jsonStr)) {
            return jsonStr;
        }

        // 调用远程服务
        List<TbContent> contentList = contentServiceProxy.loadContentListByCidService(categoryId);
        // 封装前台数据格式的广告数据
        List<BigPicture> bigPictureList = new ArrayList<>();
        for (TbContent content : contentList) {
            BigPicture bigPicture = new BigPicture();
            bigPicture.setSrcb(content.getPic());
            bigPicture.setHeight(240);
            bigPicture.setAlt(content.getTitle());
            bigPicture.setWidth(670);
            bigPicture.setSrc(content.getPic2());
            bigPicture.setWidthb(550);
            bigPicture.setHref(content.getUrl());
            bigPicture.setHeightb(240);
            bigPictureList.add(bigPicture);
        }
        //将符合前台数据格式规范的list集合，序列化成json字符串
        String str = JsonUtils.objectToJson(bigPictureList);

        // 将str缓存到redis集群中
        jedisCluster.set(contentPictureKey, str);
        // 设置这个key的生命周期，86400=1天
        jedisCluster.expire(contentPictureKey, 86400);

        return str;
    }
}
