package org.lanqiao.service;

import org.lanqiao.bean.MM;
import org.lanqiao.dao.Mmdao;

import java.sql.SQLException;

public class Mmservice {
    Mmdao dao = new Mmdao();
    //修改密码
    public int login2(String upsd1, String uid) throws SQLException {
        return dao.login2(upsd1 , uid);
    }
    //修改密码
    public MM login(String c,String upsd) throws SQLException {
        return dao.login(c,upsd);
    }
}
