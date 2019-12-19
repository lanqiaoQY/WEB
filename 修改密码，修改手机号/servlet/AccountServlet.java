package org.lanqiao.servlet;

import org.lanqiao.bean.MM;
import org.lanqiao.service.Accountservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.setCharacterEncoding("utf-8");
            resp.setContentType("text/html;charset=utf-8");
            //创建session并将成功登陆的用户保存在里面
            HttpSession session = req.getSession();
            //接收参数
            String uid = req.getParameter("uid");
            String upsd1 = req.getParameter("upsd1");
            String upsd2 = req.getParameter("upsd2");

            MM mm = new MM();
            mm.setUpsd(uid);
            mm.setUpsd(upsd1);
            mm.setUpsd(upsd2);
            //调用MmService方法
            Accountservice service = new Accountservice();

            PrintWriter out = resp.getWriter();
            //判断是否输入了账号
            if (uid != null) {
                MM a = service.login(uid);
                if (a != null) {
                    session.setAttribute("uid",uid);
                    //转发向到zhan.html
                    req.getRequestDispatcher("/Zh1.html").forward(req, resp);
                }else {
                    out.write("<html>"
                            + "<head><script type='text/javascript'> alert('修改失败！');location='Zh.html';</script></head>"
                            + "<body></body></html>");
                    return;
                }
            }else {
                //判断两次密码是否正确
                if (upsd1.equals(upsd2)) {
                    //获取账号
                    String c = (String) session.getAttribute("uid");

                    int b = service.login1(c,upsd1);

                    out.write("<html>"
                            + "<head><script type='text/javascript'> alert('修改成功！');location='landing.html';</script></head>"
                            + "<body></body></html>");
                }else {
                    out.write("<html>"
                            + "<head><script type='text/javascript'> alert('修改失败！');location='Zh.html';</script></head>"
                            + "<body></body></html>");
                    return;
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
