package com.soldier.ego.manager.service;

import com.soldier.ego.beans.EgoResult;
import com.soldier.ego.beans.PageResult;
import com.soldier.ego.beans.PictureResult;
import com.soldier.ego.rpc.pojo.TbItem;
import com.soldier.ego.rpc.pojo.TbItemDesc;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-21 下午4:38
 * @Describe: 商品信息
 **/
public interface ManagerItemService {

    /**
     * 实现商品信息的分页查询
     */
    public PageResult<TbItem> selectItemList(Integer page, Integer rows);

    /**
     * 商品信息的上架
     */
    public EgoResult reshelfItem(Long[] ids);

    /**
     * 商品信息的下架
     */
    public EgoResult instockItem(Long[] ids);

    /**
     * 删除商品信息
     */
    public EgoResult deleteItem(Long[] ids);

    /**
     * 上传商品图片
     */
    public PictureResult uploadItemPic(MultipartFile file);

    /**
     * 保存商品信息
     */
    public EgoResult saveItem(TbItem item, String desc);

    /**
     * 更新商品信息
     */
    public EgoResult updateItem(TbItem item, String desc);
}
