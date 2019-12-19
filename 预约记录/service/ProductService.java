package org.lanqiao.service;

import org.lanqiao.bean.Product;
import org.lanqiao.dao.ProductDao;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    ProductDao dao = new ProductDao();
    public List<Product> productsByCurrPage(String oid) throws SQLException {
        return dao.getTotalCount(oid);
    }

    public List<Product> bb(String oid, String state) throws SQLException {
        return dao.login(oid,state);
    }

    public int cc(String b, String c) throws SQLException {
        return dao.login2(b,c);
    }

    public List<Product> dd(String oid, String state) throws SQLException {
        return dao.login3(oid,state);
    }

    public int ccc(int b, String d) throws SQLException {
        return dao.login4(b,d);
    }
}
