package com.jiyun.bean;

import java.io.Serializable;
import java.util.List;

/**
 *    ：      --
 * 创建于： 2020/6/6 00:11
 *    邮箱：1750827655@qq.com
 */
public class CourseDrillBean implements Serializable {

    /**
     * errNo : 0
     * result : {"lists":[{"id":"68126","lesson_id":"9204","lesson_name":"建筑暖通全设计暴爽套餐","type":"1","price":"2499.00","amiba_id":"184","vip_price":"2249.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/201911/19/41/1558410v2llkbu9woereoh.jpg","specialty_id":"4,8,7,6,1,21","studentnum":"338","m_specialty_id":"6","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"69923","lesson_id":"9459","lesson_name":"建筑暖通设计全案例实战营","type":"1","price":"999.00","amiba_id":"184","vip_price":"899.10","show_vip_tag":9,"thumb":"http://newoss.zhulong.com/edu/202006/04/10/1204103gjtjojnh6y41icr.jpg","specialty_id":"4,8,7,6,1,21","studentnum":"259","m_specialty_id":"6","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"65946","lesson_id":"8996","lesson_name":"项目经理高端研修班","type":"1","price":"3999.00","amiba_id":"259","vip_price":"3799.05","show_vip_tag":95,"thumb":"https://newoss.zhulong.com/edu/201908/15/59/144459gjpg9qpso8fgcmbg.jpg","specialty_id":"5,4,12,10,7,6,1,16,0","studentnum":"552","m_specialty_id":"5","rank":"4.7","comment_html":["full_star","full_star","full_star","full_star","half_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"65109","lesson_id":"9210","lesson_name":"2020注册消防工程师协议签约班","type":"1","price":"3999.00","amiba_id":"309","vip_price":"3599.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/202001/16/48/1847480hbcqkc5mawbmjlx.jpg","specialty_id":"5,8,7,6,1","studentnum":"347","m_specialty_id":"1","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"59419","lesson_id":"8866","lesson_name":"建筑设备（水暖电）技能培训至尊VIP套餐","type":"1","price":"4999.00","amiba_id":"184","vip_price":"4499.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/201907/01/1/153301jze7ddxdjj7vkeqw.jpg","specialty_id":"4,8,13,7,6,1,21","studentnum":"125","m_specialty_id":"8","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"62793","lesson_id":"8266","lesson_name":"机电BIM工程师训练营","type":"1","price":"1999.00","amiba_id":"314","vip_price":"1799.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/202002/04/16/144616q02ajwjd1rv5k8n0.jpg","specialty_id":"5,4,8,7,6,21","studentnum":"1273","m_specialty_id":"21","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"62795","lesson_id":"8961","lesson_name":"BIM正向设计全能训练营-水暖电三大专业","type":"1","price":"1999.00","amiba_id":"314","vip_price":"1799.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/202003/12/47/184947mcq3tr2s4n7ffrca.jpg","specialty_id":"8,7,6,21","studentnum":"1432","m_specialty_id":"21","rank":"4.7","comment_html":["full_star","full_star","full_star","full_star","half_star"],"comment_htmls":"","rate":"99%","vip_tag_status":0},{"id":"67786","lesson_id":"9124","lesson_name":"2020一建协议保障班【经济】","type":"1","price":"1100.00","amiba_id":"310","vip_price":"990.00","show_vip_tag":9,"thumb":"http://newoss.zhulong.com/edu/202004/24/3/09570310r7tlbrqvjshhgz.jpg","specialty_id":"5,9,8,13,14,10,11,6,22,16,0","studentnum":"2879","m_specialty_id":"5","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"67784","lesson_id":"9041","lesson_name":"2020一建协议保障班【法规】","type":"1","price":"1100.00","amiba_id":"310","vip_price":"990.00","show_vip_tag":9,"thumb":"http://newoss.zhulong.com/edu/202004/24/27/095227qr8rnsrfekzjfrih.jpg","specialty_id":"5,9,8,13,14,10,11,6,22,16,0","studentnum":"3066","m_specialty_id":"5","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"70131","lesson_id":"8248","lesson_name":"施工全过程BIM技术应用","type":"1","price":"2499.00","amiba_id":"181","vip_price":"2249.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/tfs/pic/v1/tfs/T1kuY_BmJT1RCvBVdK.jpg","specialty_id":"5,8,7,6,15,21,16","studentnum":"1527","m_specialty_id":"21","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"47932","lesson_id":"3861","lesson_name":"建筑电气设计实操训练营【案例直播】","type":"1","price":"1999.00","amiba_id":"184","vip_price":"1799.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/201911/08/38/122438xihwxokl8rt0w6pn.jpg","specialty_id":"8,7,6","studentnum":"9039","m_specialty_id":"8","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"59413","lesson_id":"3079","lesson_name":"全国BIM等级考试一级培训【学生班】","type":"1","price":"500.00","amiba_id":"315","vip_price":"450.00","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/202002/07/52/164852ruon8lnw9i2c45em.jpg","specialty_id":"5,4,12,9,8,13,14,10,11,7,6,1,2,3,15,21,22,16,0","studentnum":"72632","m_specialty_id":"21","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"69808","lesson_id":"9508","lesson_name":"全国BIM等级考试（设备专业）联考班","type":"1","price":"2980.00","amiba_id":"178","vip_price":"2682.00","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/201911/20/44/1015447lgcg3wfunbpu73h.jpg","specialty_id":"4,8,7,6,21","studentnum":"309002","m_specialty_id":"21","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"24320","lesson_id":"4543","lesson_name":"安装造价全专业训练营（水暖电+通风消防）","type":"1","price":"1999.00","amiba_id":"154","vip_price":"1799.10","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/202001/16/3/170703etjy5fcw2qto1nrg.jpg","specialty_id":"4,8,7,6","studentnum":"13244","m_specialty_id":"4","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"100%","vip_tag_status":0},{"id":"65002","lesson_id":"4606","lesson_name":"全国BIM技能等级一级考试培训","type":"1","price":"1480.00","amiba_id":"178","vip_price":"1332.00","show_vip_tag":9,"thumb":"https://newoss.zhulong.com/edu/201911/20/7/100507q5bts4dpuoyhbgpc.jpg","specialty_id":"5,4,12,9,8,13,14,10,11,7,6,1,2,3,15,21,22,16,0","studentnum":"76823","m_specialty_id":"21","rank":"5.0","comment_html":["full_star","full_star","full_star","full_star","full_star"],"comment_htmls":"","rate":"98%","vip_tag_status":0}]}
     * exeTime : 0
     */

    private int errNo;
    private ResultBean result;
    private int exeTime;

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getExeTime() {
        return exeTime;
    }

    public void setExeTime(int exeTime) {
        this.exeTime = exeTime;
    }

    public static class ResultBean  implements Serializable{
        private List<ListsBean> lists;

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean implements Serializable {
            /**
             * id : 68126
             * lesson_id : 9204
             * lesson_name : 建筑暖通全设计暴爽套餐
             * type : 1
             * price : 2499.00
             * amiba_id : 184
             * vip_price : 2249.10
             * show_vip_tag : 9
             * thumb : https://newoss.zhulong.com/edu/201911/19/41/1558410v2llkbu9woereoh.jpg
             * specialty_id : 4,8,7,6,1,21
             * studentnum : 338
             * m_specialty_id : 6
             * rank : 5.0
             * comment_html : ["full_star","full_star","full_star","full_star","full_star"]
             * comment_htmls :
             * rate : 100%
             * vip_tag_status : 0
             */

            private String id;
            private String lesson_id;
            private String lesson_name;
            private String type;
            private String price;
            private String amiba_id;
            private String vip_price;
            private int show_vip_tag;
            private String thumb;
            private String specialty_id;
            private String studentnum;
            private String m_specialty_id;
            private String rank;
            private String comment_htmls;
            private String rate;
            private int vip_tag_status;
            private List<String> comment_html;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLesson_id() {
                return lesson_id;
            }

            public void setLesson_id(String lesson_id) {
                this.lesson_id = lesson_id;
            }

            public String getLesson_name() {
                return lesson_name;
            }

            public void setLesson_name(String lesson_name) {
                this.lesson_name = lesson_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getAmiba_id() {
                return amiba_id;
            }

            public void setAmiba_id(String amiba_id) {
                this.amiba_id = amiba_id;
            }

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
            }

            public int getShow_vip_tag() {
                return show_vip_tag;
            }

            public void setShow_vip_tag(int show_vip_tag) {
                this.show_vip_tag = show_vip_tag;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getSpecialty_id() {
                return specialty_id;
            }

            public void setSpecialty_id(String specialty_id) {
                this.specialty_id = specialty_id;
            }

            public String getStudentnum() {
                return studentnum;
            }

            public void setStudentnum(String studentnum) {
                this.studentnum = studentnum;
            }

            public String getM_specialty_id() {
                return m_specialty_id;
            }

            public void setM_specialty_id(String m_specialty_id) {
                this.m_specialty_id = m_specialty_id;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public String getComment_htmls() {
                return comment_htmls;
            }

            public void setComment_htmls(String comment_htmls) {
                this.comment_htmls = comment_htmls;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public int getVip_tag_status() {
                return vip_tag_status;
            }

            public void setVip_tag_status(int vip_tag_status) {
                this.vip_tag_status = vip_tag_status;
            }

            public List<String> getComment_html() {
                return comment_html;
            }

            public void setComment_html(List<String> comment_html) {
                this.comment_html = comment_html;
            }
        }
    }
}
