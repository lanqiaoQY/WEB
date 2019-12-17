package org.lanqiao.bean;

import java.util.List;

public class Orderlist {
    private int totalCount;//总记录数
    private List<order> data;//查询到的数据

    public Orderlist() {    }

    public Orderlist(int totalCount, List<order> data) {
        this.totalCount = totalCount;
        this.data = data;
    }

    public int getTotalCount() { return totalCount; }

    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

    public List<order> getData() { return data; }

    public void setData(List<order> data) { this.data = data; }

    @Override
    public String toString() {
        return "orderlist{" +
                "totalCount=" + totalCount +
                ", data=" + data +
                '}';
    }
}
