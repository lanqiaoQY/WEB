package org.lanqiao.bean;

public class Fang {
    private String hid;//id

    private double hmoney;//钱
    private double harea;//面积
    private String haddress;//住址
    private String housetype;//类型
    private String hphoto;//
    private String hsynopsis;//详情

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public double getHmoney() {
        return hmoney;
    }

    public void setHmoney(double hmoney) {
        this.hmoney = hmoney;
    }

    public double getHarea() {
        return harea;
    }

    public void setHarea(double harea) {
        this.harea = harea;
    }

    public String getHaddress() {
        return haddress;
    }

    public void setHaddress(String haddress) {
        this.haddress = haddress;
    }

    public String getHousetype() {
        return housetype;
    }

    public void setHousetype(String housetype) {
        this.housetype = housetype;
    }

    public String getHphoto() {
        return hphoto;
    }

    public void setHphoto(String hphoto) {
        this.hphoto = hphoto;
    }

    public String getHsynopsis() {
        return hsynopsis;
    }

    public void setHsynopsis(String hsynopsis) {
        this.hsynopsis = hsynopsis;
    }

    @Override
    public String toString() {
        return "Fang{" +
                "hid='" + hid + '\'' +
                ", hmoney=" + hmoney +
                ", harea=" + harea +
                ", haddress='" + haddress + '\'' +
                ", housetype='" + housetype + '\'' +
                ", hphoto='" + hphoto + '\'' +
                ", hsynopsis='" + hsynopsis + '\'' +
                '}';
    }
}
