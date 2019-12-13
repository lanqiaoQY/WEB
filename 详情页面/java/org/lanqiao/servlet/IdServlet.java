package org.lanqiao.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Hid")
public class IdServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
//        System.out.println("进入idServlet");
//        HttpSession s= request.getSession();
//        System.out.println("Session ID：" + s.getId());  // 获取 Session 的 ID
//        s.setAttribute("hid", "2");  // 给取的的 session 对象设置属性，属性以键值对的形式出现
//        System.out.println("hid值为：" + s.getAttribute("hid"));  // 取得 session 中的属性时
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
