package dao;

import bean.Product;
import bean.indoor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {
    QueryRunner qr = new QueryRunner(JDBCUtils.getDS());

    //全部房源数量
    public int getTotalCount() throws SQLException {
        String sql = "select count(*) from housing";
        long totalCount = (long) qr.query(sql, new ScalarHandler<>());
        return (int)totalCount;
    }
    //按指定房源查询数量
    public int getTotalCount2(String area) throws SQLException {
        String sql = "select count(*) from housing where haddress = ?";
        Object[] params = {area};
        long totalCount = (long) qr.query(sql, new ScalarHandler<>(),params);
        return (int)totalCount;
    }
    //未排序查询N条全部房源
    public List<indoor> findAll(int currPage, int pageSize) throws SQLException {
        String sql  = "select * from housing limit ? , ? ";
        int begin = (currPage-1)*pageSize; //第几条记录开始（begin）
        Object[] params = {begin , pageSize};
        List<indoor> products = qr.query(sql, new BeanListHandler<indoor>(indoor.class), params);
        return products;
    }
    //未排序查询指定房源N条
    public List<indoor> findarea(String area, int currPage, int pageSize) throws SQLException {
        String sql  = "SELECT * FROM housing WHERE haddress = ? LIMIT ?,? ";
        int begin = (currPage-1)*pageSize; //第几条记录开始（begin）
        Object[] params = {area , begin , pageSize};
        List<indoor> products = qr.query(sql, new BeanListHandler<indoor>(indoor.class), params);
        return products;
    }
    //全部房源排序
    public List<indoor> findAll2(int currPage, int pageSize,String str,String str2) throws SQLException {
        String sql  = "select * from housing order by "+str+" "+str2+" limit ? , ? ";
        int begin = (currPage-1)*pageSize; //第几条记录开始（begin）
        Object[] params = {begin , pageSize};
        List<indoor> products = qr.query(sql, new BeanListHandler<indoor>(indoor.class), params);
        return products;
    }
    //指定房源排序
    public List<indoor> findarea2(String area, int currPage, int pageSize,String str,String str2) throws SQLException {
        String sql  = "SELECT * FROM housing WHERE haddress = ? ORDER BY "+str+" "+str2+" LIMIT ?,?";
        int begin = (currPage-1)*pageSize; //第几条记录开始（begin）
        Object[] params = {area , begin , pageSize};
        List<indoor> products = qr.query(sql, new BeanListHandler<indoor>(indoor.class), params);
        return products;
    }

}
