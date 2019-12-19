package org.lanqiao.servlet;

import org.lanqiao.bean.MM;
import org.lanqiao.service.Mmservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//修改密码
@WebServlet("/MmServlet")
public class MmServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");
            //获取账号
            HttpSession session = req.getSession();
            String c = (String) session.getAttribute("uid");
            //接收参数
            String upsd = req.getParameter("upsd");
            String upsd1 = req.getParameter("upsd1");
            String upsd2 = req.getParameter("upsd2");

            MM mm = new MM();
            mm.setUid(c);
            mm.setUpsd(upsd);
            mm.setUpsd(upsd1);
            mm.setUpsd(upsd2);

            //调用MmService方法
            Mmservice service = new Mmservice();
            MM a = service.login(c,upsd);

            PrintWriter out = resp.getWriter();
            if (a != null) {
                //判断新密码是否相同
                if (upsd1.equals(upsd2) ) {
                    //调用MmService方法
                    int b = service.login2(upsd1,c);

                    String date = "1";
                    // 将数据存到request域中
                    req.setAttribute("date", date);

                    //转发向到zhan.html
                    req.getRequestDispatcher("/Keeploginservlet").forward(req, resp);
                }else {
                    out.write("<html>"
                            + "<head><script type='text/javascript'> alert('修改失败！');location='Lm.html';</script></head>"
                            + "<body></body></html>");
                    return;
                }
            }else {
                out.write("<html>"
                        + "<head><script type='text/javascript'> alert('修改失败！');location='Lm.html';</script></head>"
                        + "<body></body></html>");
                return;
            }

            } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
