package org.lanqiao.service;

import org.lanqiao.bean.indoor;
import org.lanqiao.dao.indoorDao;

import java.sql.SQLException;
import java.util.List;

public class Service {
    indoorDao dao = new indoorDao();

    public List<indoor> findAllmsg(Object id) throws SQLException {

        return dao.findAllmsg(id);

    }

}
