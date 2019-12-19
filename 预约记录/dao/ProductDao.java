package org.lanqiao.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.lanqiao.bean.Product;
import org.lanqiao.utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    QueryRunner qr = new QueryRunner(JDBCUtils.getDS());
    public List<Product> getTotalCount(String oid) throws SQLException {
        String sql = "select * from orders where ophone = ? ";
        Object[] params = {oid};
        List<Product> totalCount = qr.query(sql, new BeanListHandler<Product>(Product.class),params);
        return totalCount;
    }

    public List<Product> login(String oid, String state) throws SQLException {
        String sql = "select * from orders where ophone = ? and state = ? ";
        Object[] params = {oid,state};
        List<Product> totalCount = qr.query(sql, new BeanListHandler<Product>(Product.class),params);
        return totalCount;
    }

    public int login2(String b, String c) throws SQLException {
        String sql = "update orders set state = ? where oid = ? ";
        Object[] params = {c,b};
        int totalCount = qr.update(sql,params);
        return totalCount;
    }

    public List<Product> login3(String oid, String state) throws SQLException {
        String sql = "select oid , opdata , optime from orders where ophone = ? and state = ? ";
        Object[] params = {oid,state};
        List<Product> totalCount = qr.query(sql, new BeanListHandler<Product>(Product.class),params);
        return totalCount;
    }

    public int login4(int b, String d) throws SQLException {
        String sql = "update orders set state = ? where oid = ? ";
        Object[] params = {d,b};
        int totalCount = qr.update(sql,params);
        return totalCount;
    }
}
