package com.jiyun.frame.bean;

import java.io.Serializable;


public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 897623663351151873L;
    /**
     * uid : 14851734
     * username : 严大师
     * is_corp : 0
     * zlid : 6cbcadpvq7hePivD-59pBBUpkOPvcZauQ0vV9YrC1-PumffpaMeu5fHIXp1Cms3o
     * pcid : 14851734
     * returnpra :
     */

    public PersonHeader personHeader;
    private String uid;
    private String username;
    private String is_corp;
    private String zlid;
    public String login_name;
    public String login_password;
    private String pcid;
    private String returnpra;

    public String getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "personHeader=" + personHeader +
                ", uid='" + uid + '\'' +
                ", username='" + username + '\'' +
                ", is_corp='" + is_corp + '\'' +
                ", zlid='" + zlid + '\'' +
                ", login_name='" + login_name + '\'' +
                ", login_password='" + login_password + '\'' +
                ", pcid='" + pcid + '\'' +
                ", returnpra='" + returnpra + '\'' +
                '}';
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIs_corp() {
        return is_corp;
    }

    public void setIs_corp(String is_corp) {
        this.is_corp = is_corp;
    }

    public String getZlid() {
        return zlid;
    }

    public void setZlid(String zlid) {
        this.zlid = zlid;
    }

    public String getPcid() {
        return pcid;
    }

    public void setPcid(String pcid) {
        this.pcid = pcid;
    }

    public String getReturnpra() {
        return returnpra;
    }

    public void setReturnpra(String returnpra) {
        this.returnpra = returnpra;
    }
}
