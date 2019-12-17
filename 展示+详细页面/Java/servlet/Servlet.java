package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import bean.indoor;
import bean.indoorlist;
import service.Service;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/indoor")
public class Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        try {
            System.out.println("进入Servlet中");
            HttpSession s = request.getSession();
            //模拟取值id
            Object id = s.getAttribute("hid");//Integer.parseInt(request.getParameter("hid"));
            System.out.println("房源ID："+id);
            //查询list
            Service service = new Service();
            //取出list中的字段
            List<indoor> list = service.findAllmsg(id);
            //通过ID
            List<String> img = Arrays.asList(list.get(0).getHphoto().split("-"));
            //集合分割图片路径
            indoorlist indoorlist = new indoorlist();
            //存入indoorlist对象
            indoorlist.setList(list);
            indoorlist.setImg((img));
            //System.out.println("我是list"+list);
            ObjectMapper mapper=new ObjectMapper();
            //indoorlist转JSON格式
            String json = mapper.writeValueAsString(indoorlist);
            System.out.println("Json的所有值："+json);
            //输出JSON到页面
            response.getWriter().write(json);
            //response.sendRedirect("demo.html");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
