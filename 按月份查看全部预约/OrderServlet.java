package org.lanqiao.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lanqiao.bean.Orderlist;
import org.lanqiao.service.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/list")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            //获取到前端的数据

            int currPage = Integer.parseInt(request.getParameter("currPage"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            String key = request.getParameter("key");
            Service service = new Service();
            if(key!=null){
                String val = request.getParameter("val");
                String y = val.substring(0, 4);
                String m = val.substring(5, 7);
                String ym=y+"%"+m+"%";
                System.out.println("查询"+ym);
                Orderlist orderlist = service.findorder2(ym, currPage, pageSize);
                ObjectMapper mapper = new ObjectMapper();
                String list = mapper.writeValueAsString(orderlist);
                System.out.println(list);
                response.getWriter().write(list);
            }else {
                System.out.println("进入订单Servlet");
                Orderlist orderlist = service.findorder(currPage,pageSize);
                ObjectMapper mapper = new ObjectMapper();
                String list = mapper.writeValueAsString(orderlist);
                System.out.println(list);
                response.getWriter().write(list);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
