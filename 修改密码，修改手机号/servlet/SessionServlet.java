package org.lanqiao.servlet;

import org.lanqiao.bean.MM;
import org.lanqiao.service.Dlservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//主页判断是否登陆
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            //获取账号，密码
            HttpSession session = request.getSession();
            String b = (String) session.getAttribute("uid");
            String c = (String) session.getAttribute("upsd");
            //验证账号密码
            Dlservice service =  new Dlservice();

            MM a = service.login2(b,c);

            PrintWriter out = response.getWriter();
            //判断账号密码是否为空
            if (a != null) {
                //已登陆
                request.getRequestDispatcher("/zhan.html").forward(request, response);
            }else {
                //未登陆
                //删除session中的账号，密码
                session.removeAttribute("uid");
                session.removeAttribute("upsd");

                out.write("<html>"
                        + "<head><script type='text/javascript'> alert('请先登陆后在访问！');location='landing.html';</script></head>"
                        + "<body></body></html>");
                return;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
