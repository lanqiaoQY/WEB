package org.lanqiao.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

/**
 * 获取验证码
 */
@WebServlet("/SendSmsServlet")
public class SendSmsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//短信平台相关参数
	private String apiUrl = "https://sms_developer.zhenzikj.com";
	private String appId = "103472";
	private String appSecret = "5367191f-47e7-4307-aaa8-a65a81c6054e";
       
    public SendSmsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 短信平台使用的是榛子云短信(smsow.zhenzikj.com)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mobile = request.getParameter("mobile");//获取手机号
		System.out.println(mobile);
		try {

			JSONObject json = null;
			//生成6位验证码
			String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成的验证码
			//发送短信
			ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
			String result = client.send(mobile, "您的验证码为:" + verifyCode + "，该码有效期为5分钟，该码只能使用一次!");

			json = JSONObject.parseObject(result);
			if(json.getIntValue("code") != 0){//发送短信失败
				renderData(response, "fail");
				return; 
			}
			//将验证码存到session中,同时存入创建时间
			//以json存放，这里使用的是阿里的fastjson
			HttpSession session = request.getSession();//创建session
			json = new JSONObject();
			json.put("mobile", mobile);//手机号
			json.put("verifyCode", verifyCode);//验证码
			//json.put("createTime", System.currentTimeMillis());//创建时间  ！！！测试：暂时关闭判断时间
			// 将认证码存入SESSION
			request.getSession().setAttribute("verifyCode", json);
			renderData(response, "success");
			return ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		renderData(response, "fail");
	}
	
	protected void renderData(HttpServletResponse response, String data){
		try {
			response.setContentType("text/plain;charset=UTF-8");
			response.getWriter().write(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
