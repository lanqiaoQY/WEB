package utils;


import bean.*;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class MessageUtil {
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_EVENT_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_INEWS = "news";
    public static final String MESSAGE_MUSIC = "music";
    public static final String MESSAGE_EVENT_CLICK= "click";
    public static final String MESSAGE_EVENT_VIEW= "view";
    public static final String MESSAGE_EVENT_SCANCODE_PUSH= "scancode_push";
    public static final String MESSAGE_EVENT_LOCATION_SELECT= "location_select";


    public static Map<String,String> xml2Map(HttpServletRequest request) throws IOException, DocumentException {
        Map<String , String> map = new HashMap<>();
        ServletInputStream inputStream = request.getInputStream();
        //创建saxreader对象解析xml
        SAXReader reader = new SAXReader();
        //读取xml文件
        Document document = reader.read(inputStream);
        //获取根节点---xml
        Element root = document.getRootElement();
        //获取根节点里的子节点
        List<Element> elements = root.elements();

        for(Element e : elements){
            map.put(e.getName() , e.getText());
        }
        return map;
    }

    public static String text2XML(TextMessage message){
        XStream xStream = new XStream();
        xStream.alias("xml",TextMessage.class);
        return xStream.toXML(message);
    }

    /*
    订阅是回复用户的内容
     */
    public static String subscribeText(){
        StringBuilder sb = new StringBuilder();
        sb.append("加油！\n");
        sb.append("奥利给！\n");
        return sb.toString();
    }

    public static String getMessage(String fromUserName , String toUserName , String content){
        TextMessage message = new TextMessage();
        message.setToUserName(fromUserName);
        message.setFromUserName(toUserName);
        message.setCreateTime(new Date().getTime()+"");
        message.setMsgType(MessageUtil.MESSAGE_TEXT);
        message.setContent(content);
        return MessageUtil.text2XML(message);
    }

    /*
    将图片消息转成xml
     */
    public static String image2XML(ImageMessage message){
        XStream xStream = new XStream();
        xStream.alias("xml",ImageMessage.class);
        xStream.alias("Image", Image.class);

        return xStream.toXML(message);
    }
    public static String initImageMessage(String fromUserName, String toUserName) {
        Image image = new Image();
        //发送图片后可获得图片id
        image.setMediaId("GmRWS1VpWxVrW2L-V20O9mkj5vB_WxnHOHfdvmjNIcrr-vh557iz-1jC0U46n27q");

        ImageMessage message = new ImageMessage();
        message.setFromUserName(toUserName);
        message.setToUserName(fromUserName);
        message.setImage(image);
        message.setCreateTime(new Date().getTime()+"");
        message.setMsgType(MESSAGE_IMAGE);

        return image2XML(message);

    }

    /*
    将图文消息转成xml
     */
    public static String news2XML(NewsMessage message){
        XStream xStream = new XStream();
        xStream.alias("xml",NewsMessage.class);
        xStream.alias("item", News.class);
        return xStream.toXML(message);
    }
    public static String initNewsMessage(String fromUserName, String toUserName) {
        List<News> list = new ArrayList<>();
        News news = new News();
        news.setTitle("宝儿姐");
        news.setDescription("阿威十八式,全活不打折");
        news.setUrl("https://baijiahao.baidu.com/s?id=1596965624757107750&wfr=spider&for=pc");
        news.setPicUrl("http://anywhere.free.idcfengye.com/lq1107/img/as.jpg");
        list.add(news);

        NewsMessage message = new NewsMessage();
        message.setFromUserName(toUserName);
        message.setToUserName(fromUserName);
        message.setCreateTime(new Date().getTime()+"");
        message.setMsgType(MESSAGE_INEWS);
        message.setArticleCount(list.size());
        message.setArticles(list);

        return news2XML(message);

    }

    /*
    将音乐消息转成xml
     */
    public static String music2XML(MusicMessage message){
        XStream xStream = new XStream();
        xStream.alias("xml",MusicMessage.class);
        xStream.alias("Music",Music.class);
        return xStream.toXML(message);
    }

    public static String initMusicMessage(String fromUserName, String toUserName) {
        Music music = new Music();
        music.setTitle("RADWIMPS");
        music.setDescription("爱来爱去真的好么？！");
        music.setMusicUrl("http://loveling.vipgz2.idcfengye.com/lanqiao1104/music/RADWIMPS.mp3");
        music.setHQMusicUrl("http://loveling.vipgz2.idcfengye.com/lanqiao1104/music/RADWIMPS.mp3");
        music.setThumbMediaId("qX31vWCSLgrGq4ftqZ6B95KoYata2pLziceI8vrb7h95JR7LRTkqJkHmDIqL0IYQ");

        MusicMessage message = new MusicMessage();
        message.setFromUserName(toUserName);
        message.setToUserName(fromUserName);
        message.setCreateTime(new Date().getTime()+"");
        message.setMsgType(MESSAGE_MUSIC);
        message.setMusic(music);

        return music2XML(message);

    }
}
