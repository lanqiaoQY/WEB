package servlet;


import org.dom4j.DocumentException;
import utils.CheckSignature;
import utils.MessageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/WechatServlet")
public class WechatServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();

        try {
            //将微信传过来的xml转成map
            Map<String, String> map = MessageUtil.xml2Map(request);

            System.out.println(map);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");

            String massage = null;
            if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
                //回复1
                String content = map.get("Content");
                if("1".equals(content)){
                    massage = MessageUtil.getMessage(fromUserName,toUserName,"维鲁斯,小黄毛,卡莎,小炮,女警");
                }else if("2".equals(content)){
                    massage = MessageUtil.getMessage(fromUserName,toUserName,"妖姬,托儿索,儿童劫,球女,雷电法王");
                }else if("3".equals(content)){
                    //回复图片消息
                    massage = MessageUtil.initImageMessage(fromUserName,toUserName);
                }else if("4".equals(content)){
                    //回复图文消息
                    massage = MessageUtil.initNewsMessage(fromUserName,toUserName);
                }else if("5".equals(content)){
                    //回复音乐消息
                    massage = MessageUtil.initMusicMessage(fromUserName,toUserName);
                    System.out.println(massage);
                }
            }else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
                String event = map.get("Event");
                if(MessageUtil.MESSAGE_EVENT_SUBSCRIBE.equals(event)){
                    //给微信回复消息
                    massage = MessageUtil.getMessage(fromUserName,toUserName,MessageUtil.subscribeText());
                }else if(MessageUtil.MESSAGE_EVENT_CLICK.equalsIgnoreCase(event)){
                    //点击菜单
                    String  eventKey = map.get("EventKey");
                    if(eventKey.equals("11")){
                        //点击菜单一
                        massage = MessageUtil.getMessage(fromUserName,toUserName,"菜单一被点击");
                    }
                }else if(MessageUtil.MESSAGE_EVENT_SCANCODE_PUSH.equals(event)){
                    String  eventKey = map.get("EventKey");
                    System.out.println(eventKey);
                    if(eventKey.equals("31")){
                        //点击子菜单一
                        massage = MessageUtil.getMessage(fromUserName,toUserName,"我太难了");
                        System.out.println(massage);
                    }
                }
            }
            out.print(massage);
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        String token = "anywhere";

        String signature1 = CheckSignature.checkSignature(token,timestamp,nonce);

        if(signature1.equals(signature)){
            response.getWriter().print(echostr);
        }
    }

}
