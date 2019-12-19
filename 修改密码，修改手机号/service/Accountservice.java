package org.lanqiao.service;

import org.lanqiao.bean.MM;
import org.lanqiao.dao.Mmdao;

import java.sql.SQLException;
//忘记密码
public class Accountservice {
    Mmdao dao = new Mmdao();
    //查询账号是否存在
    public MM login(String uid) throws SQLException {
        return dao.login4(uid);
    }
    //修改密码
    public int login1(String c, String upsd1) throws SQLException {
        return dao.login5(c,upsd1);
    }
}
