package com.soldier.ego.beans;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: ego
 * @author: soldier
 * @Email: 583403411@qq.com
 * @create 19-8-21 上午11:35
 * @Describe: 封装datagrid控件需要的模型
 *                  需要实现序列化接口
 **/
public class PageResult<T> implements Serializable {

    private List<T> rows;   //对象集合
    private long total;     //总记录数

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
