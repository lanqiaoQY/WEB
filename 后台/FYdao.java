package org.lanqiao.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.lanqiao.bean.Fang;
import org.lanqiao.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FYdao {

    QueryRunner qr = new QueryRunner(JDBCUtils.getDS());

    public int fy(double hmoney, double harea, String haddress, String housetype, String hsynopsis,String substring) throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        //INSERT INTO housing(hmoney,harea,haddress,housetype,hphoto,hsynopsis) VALUES("666","1235","九龙坡","一室一厅","str","爱因斯坦做过的房子");
       // String sql= "INSERT INTO house(hid,hmoney,harea,housetype,hphoto,hsynopsis) VALUES(?,?,?,?,?,?);";
        String sql="INSERT INTO housing(hmoney,harea,haddress,housetype,hphoto,hsynopsis) VALUES(?,?,?,?,?,?);";

        Object[] arr= {hmoney,harea,haddress,housetype,substring,hsynopsis};

        int i = qr.update(sql, arr);
        return i;
    }

//    public int fy (Fang F) throws SQLException{
//
//        Connection conn = JDBCUtils.getConnection();
//        //INSERT INTO housing(hmoney,harea,haddress,housetype,hphoto,hsynopsis) VALUES("666","1235","九龙坡","一室一厅","str","爱因斯坦做过的房子");
//       // String sql= "INSERT INTO house(hid,hmoney,harea,housetype,hphoto,hsynopsis) VALUES(?,?,?,?,?,?);";
//        String sql="INSERT INTO housing(hmoney,harea,haddress,housetype,hphoto,hsynopsis) VALUES(?,?,?,?,?,?);";
//
//        Object[] arr= {F.getHmoney(),F.getHarea(),F.getHaddress(),F.getHousetype(),F.getHphoto(),F.getHsynopsis()};
//
//        int i = qr.update(sql, arr);
//       // Object[] params = {arr,sql};
//
//        return i;
//
//    }
}
