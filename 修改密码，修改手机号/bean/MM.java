package org.lanqiao.bean;

public class MM {
    private String uid;
    private String uname;
    private String upsd;
    private String openid;
    private String nickname;
    private String headimg;
    private String time;

    public MM() {

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpsd() {
        return upsd;
    }

    public void setUpsd(String upsd) {
        this.upsd = upsd;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MM{" +
                "uid='" + uid + '\'' +
                ", uname='" + uname + '\'' +
                ", upsd='" + upsd + '\'' +
                ", openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headimg='" + headimg + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
