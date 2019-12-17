package utils;

import bean.AccessToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import menu.Button;
import menu.ClickButton;
import menu.Menu;
import menu.ViewButton;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class NetUtil {

    public static final String APPID = "wx006d243c952d8821";//20191115000357288
    public static final String APPSECRET = "db5f4b2273fa27ea9e0950dfae7f6f17";//ZmGXbL49saylfaTDUdz2

    public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    public static final String GET_MENUINFO_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=ACCESS_TOKEN";
    public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    public static final String UPLOADFILE_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    public static final String GET_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

    public static String doGetStr(String urlPath) throws IOException {
        //创建URL对象，建立远程连接
        URL url = new URL(urlPath);
        //返回一个连接，连接后台和微信服务器
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //请求方式
        urlConnection.setRequestMethod("GET");
        //打开输入流，读取微信服务器返回的数据
        urlConnection.setDoInput(true);
        //因为是get请求，所有关闭输出流，不需要向微信输出
        urlConnection.setDoOutput(false);
        //打开连接
        urlConnection.connect();

        //字节流包装成高效流
        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

        //读取返回的数据
        String len;
        StringBuilder sb = new StringBuilder();
        while((len=br.readLine())!=null){
            sb.append(len);
        }

        return sb.toString();
    }

    public static String doPostStr(String urlPath , String params) throws IOException {
        URL url = new URL(urlPath);

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        urlConnection.setRequestMethod("GET");
        urlConnection.setDoInput(true);
        //打开输出流，因为要向微信服务器传递参数
        urlConnection.setDoOutput(true);
        //关闭缓存
        urlConnection.setUseCaches(false);

        //向微信服务器传递参数
        PrintWriter printWriter = new PrintWriter(urlConnection.getOutputStream());
        printWriter.write(params);

        printWriter.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

        String len;
        StringBuilder sb = new StringBuilder();
        while((len=br.readLine())!=null){
            sb.append(len);
        }

        return sb.toString();
    }

    public static AccessToken getAccessToken(){
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);

        AccessToken token = new AccessToken();
        try {
            String json = doGetStr(url);

            ObjectMapper mapper = new ObjectMapper();
            token = mapper.readValue(json,AccessToken.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return token;
    }

    public static void createMenu(AccessToken accessToken) throws IOException {
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token());

        ClickButton button1 = new ClickButton();
        button1.setType(MessageUtil.MESSAGE_EVENT_CLICK);
        button1.setName("菜单一");
        button1.setKey("11");

        ViewButton button2 = new ViewButton();
        button2.setType(MessageUtil.MESSAGE_EVENT_VIEW);
        button2.setName("菜单二");
        button2.setUrl("http://www.baidu.com/");

        ClickButton button31 = new ClickButton();
        button31.setType(MessageUtil.MESSAGE_EVENT_SCANCODE_PUSH);
        button31.setName("子菜单一");
        button31.setKey("31");

        ClickButton button32 = new ClickButton();
        button32.setType(MessageUtil.MESSAGE_EVENT_LOCATION_SELECT);
        button32.setName("子菜单二");
        button32.setKey("32");

        Button button3 = new Button();
        button3.setName("菜单三");
        button3.setSub_button(new Button[]{button31,button32});

        Menu menu = new Menu();

        menu.setButton(new Button[]{button1,button2,button3});

        ObjectMapper mapper = new ObjectMapper();
        String params = mapper.writeValueAsString(menu);
        System.out.println(params);

        //{"errcode":0,"errmsg":"ok"}
        String response = doPostStr(url, params);
        System.out.println(response);
        Map<String, Object> map = (Map<String, Object>)mapper.readValue(response, new TypeReference<Map<String, Object>>() {});

        int errcode = -1;
        errcode = (int) map.get("errcode");

        if(errcode == 0){
            System.out.println("菜单创建成功");
        }else {
            System.out.println("菜单创建失败");
        }
    }

    /*
        获取菜单信息
     */
    public static String getMenuInfo(AccessToken accessToken) throws IOException {
        String url = GET_MENUINFO_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token());

        String result = doGetStr(url);

        return result;
    }

    /*
        删除菜单
     */
    public static void deleteMenu(AccessToken accessToken) throws IOException {
        String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", accessToken.getAccess_token());

        String result = doGetStr(url);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = (Map<String, Object>)mapper.readValue(result, new TypeReference<Map<String, Object>>() {});

        int errcode = -1;

        errcode  = (int) map.get("errcode");

        if(errcode == 0){
            System.out.println("删除菜单成功");
        }else{
            System.out.println("删除菜单失败");
        }
    }

    public static String uploadFile(String filePath , AccessToken token , String type) throws IOException {
        File file = new File(filePath);

        if(!file.isFile() || !file.exists()){
            throw new IOException("文件不存在");
        }

        String urlPath = UPLOADFILE_URL.replace("ACCESS_TOKEN",token.getAccess_token()).replace("TYPE",type);

        URL url = new URL(urlPath);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);

        //设置请求头
        connection.setRequestProperty("Connection","Keep-Alive");
        connection.setRequestProperty("Charset","UTF-8");

        String boundary = "-----------"+System.currentTimeMillis();

        connection.setRequestProperty("Content-Type","multipart/form-data; boundary="+boundary);

        //写参数
        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(boundary);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; name=\"media\"; filename=\""+file.getName()+"\"\r\n"
                + "Content-Type:application/octet-stream\r\n\r\n");
        System.out.println(sb.toString());

        //写头
        OutputStream os = new DataOutputStream(connection.getOutputStream());
        os.write(sb.toString().getBytes("utf-8"));

        //写文件
        DataInputStream is = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bys = new byte[1024];
        while((bytes = is.read(bys))!=-1){
            os.write(bys,0,bytes);
        }
        is.close();

        //写边界
        String foot = "\n\r--"+boundary+"--"+"\n\r";
        System.out.println(foot);
        os.write(foot.getBytes("utf-8"));

        os.flush();
        os.close();

        //处理响应
        //connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));

        String len;
        StringBuilder sb1 = new StringBuilder();
        while ((len = br.readLine())!= null){
            sb1.append(len);
        }

        return sb1.toString();
    }

    public static void getMedia(AccessToken token , String media_id) throws IOException {
        String url = GET_MEDIA_URL.replace("ACCESS_TOKEN",token.getAccess_token()).replace("MEDIA_ID",media_id);

        System.out.println(url);
        doGetStr(url);

    }
}
