package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import bean.indoor;
import utils.JDBCUtils;

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

}
