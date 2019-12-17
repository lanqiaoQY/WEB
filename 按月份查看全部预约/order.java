package org.lanqiao.bean;

public class order {
    private int oid;
    private String oname;
    private String osex;
    private String ophone;
    private String opdata;
    private String optime;
    private String otime;
    private int hid;

    public order() {
    }

    public order(int oid, String oname, String osex, String ophone, String opdata, String optime, String otime, int hid) {
        this.oid = oid;
        this.oname = oname;
        this.osex = osex;
        this.ophone = ophone;
        this.opdata = opdata;
        this.optime = optime;
        this.otime = otime;
        this.hid = hid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOsex() {
        return osex;
    }

    public void setOsex(String osex) {
        this.osex = osex;
    }

    public String getOphone() {
        return ophone;
    }

    public void setOphone(String ophone) {
        this.ophone = ophone;
    }

    public String getOpdata() {
        return opdata;
    }

    public void setOpdata(String opdata) {
        this.opdata = opdata;
    }

    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    public String getOtime() {
        return otime;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    @Override
    public String toString() {
        return "order{" +
                "oid=" + oid +
                ", oname='" + oname + '\'' +
                ", osex='" + osex + '\'' +
                ", ophone='" + ophone + '\'' +
                ", opdata='" + opdata + '\'' +
                ", optime='" + optime + '\'' +
                ", otime='" + otime + '\'' +
                ", hid=" + hid +
                '}';
    }
}
