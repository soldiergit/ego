package com.soldier.ego.manager.service.impl;

import com.soldier.ego.beans.*;
import com.soldier.ego.manager.service.ManagerItemService;
import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.rpc.pojo.TbItemDesc;
import com.soldier.ego.rpc.pojo.TbItemParamItem;
import com.soldier.ego.rpc.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-8-21下午4:39
 * @Describe:商品信息
 **/
@Service
public class ManagerItemServiceImpl implements ManagerItemService {

    //注入的是远程服务的代理对象
    @Autowired
    private ItemService itemServiceProxy;

    /**
     * 通过spring的EL表达式注入ftp信息
     */
    @Value("${FTP_HOST}")
    private String FTP_HOST;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_PATH}")
    private String FTP_PATH;
    @Value("${IMAGE_HTTP_PATH}")
    private String IMAGE_HTTP_PATH;

    @Override
    public PageResult<TbItem> selectItemList(Integer page, Integer rows) {
        return itemServiceProxy.selectItemListService(page, rows);
    }

    @Override
    public EgoResult reshelfItem(Long[] ids) {
        //将ids数组转为List集合
        List<Long> itemIds = Arrays.asList(ids);
        //调用远程服务
        return itemServiceProxy.updateItemStatusService(itemIds, true);
    }

    @Override
    public EgoResult instockItem(Long[] ids) {
        //将ids数组转为List集合
        List<Long> itemIds = Arrays.asList(ids);
        //调用远程服务
        return itemServiceProxy.updateItemStatusService(itemIds, false);
    }

    @Override
    public EgoResult deleteItem(Long[] ids) {
        //将ids数组转为List集合
        List<Long> itemIds = Arrays.asList(ids);
        //调用远程服务
        return itemServiceProxy.deleteItemService(itemIds);
    }

    @Override
    public PictureResult uploadItemPic(MultipartFile file) {
        //是否上传成功
        boolean flag = false;
        //重新命名后的文件名
        String fileName = null;

        //获取上传文件的流
        InputStream inputStream = null;
        try {
            //获取新的文件名字
            fileName = IDUtils.genImageName();
            //换取上传的文件的原始名字
            String originalFilename = file.getOriginalFilename();
            //获取文件的扩展名--后缀
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
            //拼接新的名字
            fileName = fileName + ext;

            //获取文件输入流
            inputStream = file.getInputStream();

            //实现文件上传到ftp
            flag = FtpUtils.uploadFile(FTP_HOST, FTP_PORT, FTP_USERNAME, FTP_PASSWORD, FTP_PATH ,fileName, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }

        PictureResult result = null;
        if (flag) {
            result = new PictureResult();
            result.setError(0);
            result.setUrl(IMAGE_HTTP_PATH+"/"+fileName);
            result.setMessage("ok");
        } else {
            result = new PictureResult();
            result.setError(1);
            result.setUrl("url");
            result.setMessage("error");
        }
        return result;
    }

    @Override
    public EgoResult saveItem(TbItem item, String desc, String itemParams) {

        //当前时间
        Date date = new Date();

        //给item对象封装数据
        long itemId = IDUtils.genItemId();  //自己产生商品的id，满足后期的分库分表的需求
        item.setId(itemId);
        item.setStatus((byte) 1);
        item.setCreated(date);
        item.setUpdated(date);

        //给itemDesc对象封装数据
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(itemId);
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);

        //给itemParam对象封装数据
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setParamData(itemParams);
        itemParamItem.setId(itemId);
        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);

        return itemServiceProxy.saveItemService(item, itemDesc, itemParamItem);
    }

    @Override
    public EgoResult updateItem(TbItem item, String desc, String itemParams) {

        //当前时间
        Date date = new Date();

        //给item对象封装数据
        item.setUpdated(date);

        //给itemDesc对象封装数据
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        itemDesc.setCreated(date);
        itemDesc.setUpdated(date);

        //给itemParamItem对象封装数据
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setParamData(itemParams);
        itemParamItem.setItemId(item.getId());
//        itemParamItem.setCreated(date);
        itemParamItem.setUpdated(date);

        //调用远程服务
        return itemServiceProxy.updateItemService(item, itemDesc, itemParamItem);
    }
}
