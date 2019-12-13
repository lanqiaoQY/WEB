package org.lanqiao.bean;

import java.util.List;

public class indoorlist {
    //字段集合
    private List<indoor> list;
    //图片路径集合
    private List<String> img ;

    public List<indoor> getList() {
        return list;
    }

    public void setList(List<indoor> list) {
        this.list = list;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }
}
