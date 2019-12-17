package org.lanqiao.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.lanqiao.bean.Orderlist;
import org.lanqiao.bean.indoor;
import org.lanqiao.bean.order;
import org.lanqiao.utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class indoorDao {
    QueryRunner qr = new QueryRunner(JDBCUtils.getDS());

    public List<indoor> findAllmsg(Object id) throws SQLException {
        System.out.println("查询数据库中");
        String sql = "select * from housing where hid = ?";
        List<indoor> list = qr.query(sql, new BeanListHandler<indoor>(indoor.class),id);
        return list;
    }

    public List<order> orderdata(int currPage, int pageSize) throws SQLException {
        System.out.println("查询订单数据库中");
        String sql  = "select * from orders limit ? , ? ";
        int begin = (currPage-1)*pageSize; //第几条记录开始（begin）
        Object[] params = {begin , pageSize};
        List<order> products = qr.query(sql, new BeanListHandler<order>(order.class), params);
        return products;
    }

    public int getOrderTotalCount() throws SQLException {//页数
        String sql = "select count(*) from orders";
        long totalCount = (long) qr.query(sql, new ScalarHandler<>());
        return (int)totalCount;
    }
//--------------------------------------------------------------------------

    public List<order> orderdata2(String ym ,int currPage, int pageSize) throws SQLException {
        System.out.println("按日期查询数据库订单中");
        String sql = "SELECT * FROM orders WHERE opdata LIKE ? LIMIT ? , ?";
        int begin = (currPage-1)*pageSize; //第几条记录开始（begin）
        Object[] params = {ym,begin , pageSize};
        List<order> products = qr.query(sql,new BeanListHandler<order>(order.class),params);
        return products;
    }
//
    public int getOrderTotalCount2(String ym) throws SQLException {//页数
        String sql = "SELECT COUNT(*) FROM orders WHERE opdata LIKE ?";
        long totalCount = (long) qr.query(sql, new ScalarHandler<>(),ym);
        return (int)totalCount;
    }
}
