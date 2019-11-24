package com.soldier.ego.search.service.impl;

import com.soldier.ego.beans.JsonUtils;
import com.soldier.ego.rpc.pojo.TbItemParamItem;
import com.soldier.ego.rpc.service.ItemParamItemService;
import com.soldier.ego.search.service.SearchItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName:ego
 * @author:soldier
 * @Email:583403411@qq.com
 * @create:19-11-24上午8:56
 * @Describe:
 **/
@Service
public class SearchItemParamItemServiceImpl implements SearchItemParamItemService {

    //注入的是远程服务的代理对象
    @Autowired
    private ItemParamItemService itemParamItemServiceProxy;

    @Override
    public String loadItemParamItemService(Long itemId) {

        TbItemParamItem itemParamItem = itemParamItemServiceProxy.loadItemParamItemListService(itemId);

        //  获得商品规格参数信息，是json字符串
        String paramData = itemParamItem.getParamData();

        return paramData.isEmpty() ? null : getItemParamData(paramData);
    }

    /**
     * 将json字符串转为html代码
     */
    private String getItemParamData(String paramData) {
        //  解析json字符串
        List<Map> mapList = JsonUtils.jsonToList(paramData, Map.class);

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
        stringBuffer.append("    <tbody>\n");
        for (Map m1 : mapList) {
            stringBuffer.append("        <tr>\n");
            stringBuffer.append("            <th class=\"tdTitle\" colspan=\"2\">"
                    + m1.get("group") + "</th>\n");
            stringBuffer.append("        </tr>\n");
            List<Map> list2 = (List<Map>) m1.get("params");
            for (Map m2 : list2) {
                stringBuffer.append("        <tr>\n");
                stringBuffer.append("            <td class=\"tdTitle\">" + m2.get("k")
                        + "</td>\n");
                stringBuffer.append("            <td>" + m2.get("v") + "</td>\n");
                stringBuffer.append("        </tr>\n");
            }
        }
        stringBuffer.append("    </tbody>\n");
        stringBuffer.append("</table>");

        return stringBuffer.toString();
    }
}
