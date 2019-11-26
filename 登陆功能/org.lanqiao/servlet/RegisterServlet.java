package org.lanqiao.servlet;

import com.alibaba.fastjson.JSONObject;
import org.lanqiao.bean.Users;
import org.lanqiao.service.UsersService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 注册
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;


  public RegisterServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=utf-8");
    response.setCharacterEncoding("utf-8");

    String mobile = request.getParameter("mobile");//获取手机号
    String verifyCode = request.getParameter("verifyCode");//获取验证码
    String key = request.getParameter("key");//判断是注册还是登陆有参则为注册

    System.out.println(mobile + "------" + verifyCode + "-----" + key);

    JSONObject json = (JSONObject) request.getSession().getAttribute("verifyCode");//获取session中的验证码
    if (json == null) {
      response.getWriter().write("验证码错误");

    } else if (!json.getString("mobile").equals(mobile)) {
      // renderData(response, "手机号错误");
      response.getWriter().write("手机号错误");

    } else if (!json.getString("verifyCode").equals(verifyCode)) {

      response.getWriter().write("验证码错误");

    } /*else if ((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5) {//测试：暂时不判断验证码过期

      response.getWriter().write("验证码已过期");

    } */else {
        System.out.println("登陆成功");
      response.sendRedirect("http://liuh.vipgz2.idcfengye.com/servletWEB_war/zhan.html");//跳转展示页面
      } 
    }
  }

  

}
