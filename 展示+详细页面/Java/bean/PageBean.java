package bean;

import java.util.List;

public class PageBean {
    private int totalCount;//总记录数
    private List<indoor> data;//查询到的数据

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<indoor> getData() {
        return data;
    }

    public void setData(List<indoor> data) {
        this.data = data;
    }
}
