package com.soldier.ego.manager.controller;

import com.soldier.ego.beans.PictureResult;
import com.soldier.ego.manager.service.ManagerItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-9-5 下午7:12
 * @Describe: 商品图片
 **/
@Controller
public class ItemImageController {

    //注入service对象
    @Autowired
    private ManagerItemService managerItemService;

    /**
     * 处理图片上传请求
     *      参数uploadFile必须与前端命名一致
     */
    @RequestMapping(value = "pic/upload", produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public PictureResult uploadFile(MultipartFile uploadFile) {
        return managerItemService.uploadItemPic(uploadFile);
    }
}
