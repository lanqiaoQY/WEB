package org.lanqiao.bean;

import java.util.List;

public class Page {
    private Long totalCount;//总记录数
    private List<Product> data;//查询到的数据

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
