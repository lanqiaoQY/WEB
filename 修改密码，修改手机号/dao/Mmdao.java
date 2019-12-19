package org.lanqiao.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.lanqiao.bean.MM;
import org.lanqiao.utils.JDBCUtils;

import java.sql.SQLException;

public class Mmdao {
    QueryRunner qr = new QueryRunner(JDBCUtils.getDS());
    //修改密码
    public int login2(String upsd1, String uid) throws SQLException {
        String sql = "update xxx set upsd = ? where uid = ?";
        Object[] upsd2 = {upsd1,uid};
        int mm2 = qr.update(sql,upsd2);
        return mm2;
    }
    //修改密码
    public MM login(String c,String upsd) throws SQLException {
        String sql = "select * from xxx where uid= ? and upsd = ? ";
        Object[] uid1 = {c,upsd};
        MM mm1 = qr.query(sql, new BeanHandler<>(MM.class), uid1);
        return mm1;
    }
    //登陆判断
    public MM login1(String uid,String upsd) throws SQLException {
        String sql = "select * from xxx where uid= ? and upsd = ? ";
        Object[] uid1 = {uid,upsd};
        MM mm1 = qr.query(sql, new BeanHandler<>(MM.class), uid1);
        return mm1;
    }

    public MM login4(String uid) throws SQLException {
        String sql = "select * from xxx where uid= ?";
        Object[] uid1 = {uid};
        MM mm1 = qr.query(sql, new BeanHandler<>(MM.class), uid1);
        return mm1;
    }

    public int login5(String c, String upsd1) throws SQLException {
        String sql = "update xxx set upsd = ? where uid = ? ";
        Object[] uid1 = {upsd1,c};
        int mm1 = qr.update(sql,uid1);
        return mm1;
    }

    public MM login6(String b, String c) throws SQLException {
        String sql = "select * from xxx where uid= ? and upsd = ? ";
        Object[] uid1 = {b,c};
        MM mm1 = qr.query(sql, new BeanHandler<>(MM.class), uid1);
        return mm1;
    }
}
