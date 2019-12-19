package org.lanqiao.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lanqiao.bean.Product;
import org.lanqiao.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/products.do")
public class RecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("text/html;charset=utf-8");
            resp.setCharacterEncoding("utf-8");
            //获取手机号
            String oid = "15023134459";
            //状态码固定为1
            String state = "1";

            ProductService service = new ProductService();
            //获取key值
            String a = req.getParameter("key");
            //获取该用户的所以记录
            if (a == null){
                List<Product> pageBean = service.productsByCurrPage(oid);

                ObjectMapper mapper = new ObjectMapper();
                String response = mapper.writeValueAsString(pageBean);
                resp.getWriter().write(response);
            }
            //获取该用户可以取消的订单
            else if (a.equals("1")){

                ObjectMapper mapper = new ObjectMapper();

                List<Product> pageBean1 = service.dd(oid,state);

                for (Product s:pageBean1) {

                    String data=s.getOpdata();
                    String time=s.getOptime();
                    int b =s.getOid();

                    long l = ThinkTime(data, time);

                    if(l > 0) {
                        String d = "2";
                        service.ccc(b,d);
                    }

                }
                List<Product> pageBean = service.bb(oid,state);

                String response = mapper.writeValueAsString(pageBean);
                resp.getWriter().write(response);
            }
            //取消预约
            else if (a.equals("2")){
                String data = req.getParameter("c");

                    String b = data.substring(8,10);

                    String c = "0";

                    service.cc(b,c);


            }
        } catch (SQLException e ) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public long ThinkTime(String data, String time) throws ParseException {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");//可以方便地修改日期格式
        String str = dateFormat.format(now);//获取当前时间

        //SimpleDateFormat formatdata = new SimpleDateFormat("yyyy-MM-dd HH");

        Date date = dateFormat.parse(str);
        String str2 = data + " " + time;//获取的用户选择时间
        Date date1 = dateFormat.parse(str2);

        long time1 = date.getTime();//转换的系统时间
        long time2 = date1.getTime();//转换的用户选择的时间

        return time1 - time2;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
