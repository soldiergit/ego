package com.soldier.ego.search.controller;

import com.soldier.ego.search.service.ImportItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-25上午9:28
 * @Describe:处理数据库数据导入到SolrCloud索引库
 * http://localhost:8082/import/importData.html
 **/
@Controller
@RequestMapping("/import")
public class ImportDataController {

    @Autowired
    private ImportItemService importItemService;

    @RequestMapping("/importData")
    public String importData() {
        importItemService.importItemService();
        return "importSuccess";
    }
}
