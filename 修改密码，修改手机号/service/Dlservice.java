package org.lanqiao.service;

import org.lanqiao.bean.MM;
import org.lanqiao.dao.Mmdao;

import java.sql.SQLException;

public class Dlservice {
    Mmdao dao = new Mmdao();
    //登陆判断
    public MM login1(String uid, String upsd) throws SQLException {
        return dao.login1(uid,upsd);
    }

    public MM login2(String b, String c) throws SQLException {
        return dao.login6(b,c);
    }
}
