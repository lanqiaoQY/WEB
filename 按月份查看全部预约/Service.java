package org.lanqiao.service;

import org.lanqiao.bean.Orderlist;
import org.lanqiao.bean.indoor;
import org.lanqiao.bean.order;
import org.lanqiao.dao.indoorDao;

import java.sql.SQLException;
import java.util.List;

public class Service {
    indoorDao dao = new indoorDao();

    public List<indoor> findAllmsg(Object id) throws SQLException {
        return dao.findAllmsg(id);
    }

    public Orderlist findorder(int currPage, int pageSize) throws SQLException {
        List<order> list = dao.orderdata(currPage,pageSize);
        int totalCount = dao.getOrderTotalCount();
        Orderlist orderlist = new Orderlist();
        orderlist.setTotalCount(totalCount);
        orderlist.setData(list);
        return orderlist;
    }

    public Orderlist findorder2(String ym,int currPage, int pageSize) throws SQLException {
        List<order> list = dao.orderdata2(ym,currPage,pageSize);
        int totalCount = dao.getOrderTotalCount2(ym);
        Orderlist orderlist = new Orderlist();
        orderlist.setTotalCount(totalCount);
        orderlist.setData(list);
        return orderlist;
    }


}
