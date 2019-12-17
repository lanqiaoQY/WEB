package service;

import bean.PageBean;
import bean.Product;
import bean.indoor;
import dao.ProductDao;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    //全部
    public PageBean productsByCurrPage(int currPage, int pageSize, int sort) throws SQLException {
        ProductDao dao = new ProductDao();
        int totalCount;
        List<indoor> list;
        if (sort == 0) {
            totalCount = dao.getTotalCount();
            list = dao.findAll(currPage , pageSize);
        }else if (sort == 1) {
            String str = "hmoney";
            String str2 = "desc";
            totalCount = dao.getTotalCount();
            list = dao.findAll2(currPage , pageSize , str , str2);
        }else if (sort == 2) {
            String str = "hmoney";
            String str2 = "asc";
            totalCount = dao.getTotalCount();
            list = dao.findAll2(currPage , pageSize , str , str2);
        }else if (sort == 3) {
            String str = "harea";
            String str2 = "desc";
            totalCount = dao.getTotalCount();
            list = dao.findAll2(currPage , pageSize , str , str2);
        }else if (sort == 4) {
            String str = "harea";
            String str2 = "asc";
            totalCount = dao.getTotalCount();
            list = dao.findAll2(currPage , pageSize , str , str2);
        }else {
            String str = "reservations";
            String str2 = "desc";
            totalCount = dao.getTotalCount();
            list = dao.findAll2(currPage , pageSize , str , str2);
        }
        PageBean pageBean = new PageBean();
        pageBean.setTotalCount(totalCount);
        for (int i = 0; i < list.size(); i++) {
            String[] in = list.get(i).getHphoto().split("-");
            list.get(i).setHphoto(in[1]);
        }
        pageBean.setData(list);

        return pageBean;

    }
    //地区
    public PageBean areas(String area,int currPage, int pageSize, int sort) throws SQLException {
        ProductDao dao = new ProductDao();
        int totalCount;
        List<indoor> list;
        if (sort == 0) {
            totalCount = dao.getTotalCount2(area);
            list = dao.findarea(area,currPage , pageSize);
        }else if (sort == 1) {
            String str = "hmoney";
            String str2 = "desc";
            totalCount = dao.getTotalCount2(area);
            list = dao.findarea2(area,currPage , pageSize,str,str2);
        }else if (sort == 2) {
            String str = "hmoney";
            String str2 = "asc";
            totalCount = dao.getTotalCount2(area);
            list = dao.findarea2(area,currPage , pageSize,str,str2);
        }else if (sort == 3) {
            String str = "harea";
            String str2 = "desc";
            totalCount = dao.getTotalCount2(area);
            list = dao.findarea2(area,currPage , pageSize,str,str2);
        }else if (sort == 4) {
            String str = "harea";
            String str2 = "asc";
            totalCount = dao.getTotalCount2(area);
            list = dao.findarea(area,currPage , pageSize);
        }else {
            String str = "reservations";
            String str2 = "desc";
            totalCount = dao.getTotalCount2(area);
            list = dao.findarea2(area,currPage , pageSize,str,str2);
        }
        PageBean pageBean = new PageBean();
        pageBean.setTotalCount(totalCount);
        for (int j = 0 ; j < list.size() ; j++){
            String[] arr = list.get(j).getHphoto().split("-");
            list.get(j).setHphoto(arr[1]);
        }
        pageBean.setData(list);
        return pageBean;

    }
}
