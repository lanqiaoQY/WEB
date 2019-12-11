package org.lanqiao.service;

import org.lanqiao.bean.Fang;
import org.lanqiao.dao.FYdao;

import java.sql.SQLException;

public class FYService {
    FYdao dao = new FYdao();

    public int add(double hmoney, double harea, String haddress, String housetype, String hsynopsis,String substring) throws SQLException {
        return dao.fy(hmoney,harea,  haddress,  housetype, hsynopsis,substring);
    }

//    public int add(Fang f) throws SQLException {
//
//    }

//    public int add(double hmoney, double harea, String haddress, String housetype, String hsynopsis) {
//    }
}
