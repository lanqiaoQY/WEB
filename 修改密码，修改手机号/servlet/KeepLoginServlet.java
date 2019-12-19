package org.lanqiao.servlet;

import org.lanqiao.bean.MM;
import org.lanqiao.service.Dlservice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

//登陆和退出登陆
@WebServlet("/Keeploginservlet")
public class KeepLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            //接收参数
            String uid = request.getParameter("uid");
            String upsd = request.getParameter("upsd");

            PrintWriter out = response.getWriter();

            //创建session并将成功登陆的用户保存在里面
            HttpSession session = request.getSession();
            //查询session中是否有账号存在
            String d = (String) session.getAttribute("uid");
            if (d == null) {
                //登陆
                Dlservice service =  new Dlservice();

                MM a = service.login1(uid,upsd);

                if (a == null) {
                    //登陆失败
                    out.write("<html>"
                            + "<head><script type='text/javascript'> alert('账号或密码错误!');location='landing.html';</script></head>"
                            + "<body></body></html>");
                    return;
                } else {
                        //登陆成功
                        session.setAttribute("uid",uid);
                        session.setAttribute("upsd",upsd);
                        session.setMaxInactiveInterval(24*60*60);//过期时间。
                        //获取登陆账号，密码
                        String b = (String) session.getAttribute("uid");
                        String c = (String) session.getAttribute("upsd");
                        //转发向到zhan.html
                        request.getRequestDispatcher("/zhan.html").forward(request, response);
                }
            }else {
                String message = (String)request.getAttribute("date");
                if (message == "1"){
                    //修改密码成功后退出登陆
                    //删除session中的账号，密码
                    session.removeAttribute("uid");
                    session.removeAttribute("upsd");

                    out.write("<html>"
                            + "<head><script type='text/javascript'> alert('修改成功，请重新登陆.');location='landing.html';</script></head>"
                            + "<body></body></html>");
                    return;
                }else {
                    //退出登陆
                    //删除session中的账号，密码
                    session.removeAttribute("uid");
                    session.removeAttribute("upsd");

                    out.write("<html>"
                            + "<head><script type='text/javascript'> alert('退出成功!');location='landing.html';</script></head>"
                            + "<body></body></html>");
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}