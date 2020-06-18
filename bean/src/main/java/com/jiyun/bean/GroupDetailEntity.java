package com.jiyun.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhen
 * @date 2019/11/09
 */
public class GroupDetailEntity implements Serializable {


    private static final long serialVersionUID = 1762290585841857882L;
    /**
     * groupinfo : {"gid":"200101","group_name":"居住建筑","fup":"2","fid":"3","private":"0","founder_uid":"10215338","founder_name":"菊爽爽","introduce":"本小组汇集住宅建筑及别墅建筑的方案文本、方案图及施工图。建筑师朋友们可以自由分享与下载独栋别墅、高层住宅、多层住宅、高层住宅楼、住宅区规划、塔式住宅、板式住宅、公寓及宿舍楼、底商住宅、联排别墅、双拼别墅、新农村自建房等的方案文本、方案图及施工图，我分享我快乐！","apply_word":"居住建筑","tag":"","create_time":"1465205975","thread_balance":"10","post_balance":"5","day_balance":"5000","day_credit":"500","mon_balance":"100000","mon_credit":"100000","is_specialty":"1","level":"1","is_authority":"1","is_verification":"0","is_vip":"0","is_auth":"0","phone":"13146544654","qq":"","status":"1","assessor_uid":"7170569","assessor_name":"zl320","assessor_ip":"127.0.0.1","is_vip_download":"0","is_strangeradd":"1","special":"2","is_downreply":"0","star_coin":"","group_hot":"0","member_num":"135329","thread_num":"8653","post_num":"14626","balance_num":"3300","credit_num":"8","new_member":"120","essence_num":"279","new_apply":"0","month_hot":"10515","sum_hot":"359831","logo":"https://avatar.zhulong.com/group/000/20/01/01_logo_big.jpg","dateline":"2016-06-06","is_add":1}
     * thread_list : [{"tid":"20094205","gid":"200101","title":"河南某二十四层住宅楼建筑结构施工图","pic":"http://newoss.zhulong.com/tfs/pic/v1/tfs/T1aLY_ByWv1RCvBVdK.jpg","create_time":"1126226340","view_num":"4310","reply_num":"1","username":"常正非","uid":"333019","is_essence":"1","user_img":"http://avatar.zhulong.com/avatar/000/33/30/19_avatar_small.jpg"},{"tid":"20103128","gid":"200101","title":"某高层综合住宅楼建筑施工图（全套）","pic":"http://newoss.zhulong.com/tfs/pic/v1/tfs/T1C_x_BKdg1RCvBVdK.jpg","create_time":"1131584100","view_num":"3798","reply_num":"3","username":"benqjoy","uid":"638426","is_essence":"0","user_img":"http://avatar.zhulong.com/avatar/000/63/84/26_avatar_small.jpg"},{"tid":"20118778","gid":"200101","title":"[深圳]某31层塔式商住楼建筑施工图","pic":"http://newoss.zhulong.com/tfs/pic/v1/tfs/T1n9A_BmA_1RCvBVdK.jpg","create_time":"1140402780","view_num":"8389","reply_num":"3","username":"gonghaijun","uid":"261706","is_essence":"0","user_img":"http://avatar.zhulong.com/avatar/000/26/17/06_avatar_small.jpg"},{"tid":"20119875","gid":"200101","title":"东莞市虎门都市华庭施工图","pic":"http://newoss.zhulong.com/tfs/pic/v1/tfs/T1hLY_BTbT1RCvBVdK.jpg","create_time":"1140749640","view_num":"3090","reply_num":"1","username":"renpeng","uid":"22687","is_essence":"0","user_img":"http://avatar.zhulong.com/avatar/000/02/26/87_avatar_small.jpg"},{"tid":"20130210","gid":"200101","title":"某二十八层公寓式住宅楼建筑施工图含效果图","pic":"http://newoss.zhulong.com/tfs/pic/v1/tfs/T1uhd_B7L_1RCvBVdK.jpg","create_time":"1144112280","view_num":"18214","reply_num":"5","username":"朱雀不信邪","uid":"758873","is_essence":"0","user_img":"http://avatar.zhulong.com/avatar/000/75/88/73_avatar_small.jpg"},{"tid":"20143664","gid":"200101","title":"深圳某小区住宅群建筑施工图","pic":"http://newoss.zhulong.com/tfs/pic/v1/tfs/T1I_D_B7Ev1RCvBVdK.jpg","create_time":"1149468420","view_num":"5560","reply_num":"2","username":"wjy2687032","uid":"173337","is_essence":"0","user_img":"http://avatar.zhulong.com/avatar/000/17/33/37_avatar_small.jpg"},{"tid":"20163287","gid":"200101","title":"重庆阳光华庭方案设计文本","pic":"http://newoss.zhulong.com/tfs/pic/v1/tfs/T1.LY_B4Kv1RCvBVdK.jpg","create_time":"1156122720","view_num":"2566","reply_num":"1","username":"eggs","uid":"75002","is_essence":"0","user_img":"http://avatar.zhulong.com/avatar/000/07/50/02_avatar_small.jpg"},{"tid":"20164276","gid":"200101","title":"温州市花水远景小区设计文本","pic":"http://newoss.zhulong.com/tfs/pic/v1/tfs/T1B_Z_BTb_1RCvBVdK.jpg","create_time":"1156467000","view_num":"2382","reply_num":"1","username":"eggs","uid":"75002","is_essence":"0","user_img":"http://avatar.zhulong.com/avatar/000/07/50/02_avatar_small.jpg"},{"tid":"20164570","gid":"200101","title":"深圳华侨城中心花园设计文本","pic":"http://newoss.zhulong.com/tfs/pic/v1/tfs/T1YhZ_BbZ_1RCvBVdK.jpg","create_time":"1156726320","view_num":"2434","reply_num":"1","username":"zkmzkm","uid":"881135","is_essence":"0","user_img":"http://avatar.zhulong.com/avatar/000/88/11/35_avatar_small.jpg"},{"tid":"20164884","gid":"200101","title":"华筑凤凰城小区规划设计文本","pic":"http://newoss.zhulong.com/tfs/pic/v1/tfs/T1V_x_B7Vg1RCvBVdK.jpg","create_time":"1156813380","view_num":"2413","reply_num":"1","username":"zkmzkm","uid":"881135","is_essence":"0","user_img":"http://avatar.zhulong.com/avatar/000/88/11/35_avatar_small.jpg"}]
     * tag_arr : [{"tag_name":"资料属性","select_name":"建筑方案图","on":1,"selects":[{"on":1,"name":"建筑方案图","url":"s1"},{"on":0,"name":"建筑方案文本","url":"s2"},{"on":0,"name":"建筑施工图","url":"s3"}]},{"id":"4798","gid":"200101","tag_name":"图纸深度","comment":["方案（初设图）","扩初图","施工图","竣工图"],"select_name":"建筑方案图","sort":"1","selects":[{"name":"方案（初设图）","on":0,"url":"s1a1"},{"name":"扩初图","on":0,"url":"s1a2"},{"name":"施工图","on":0,"url":"s1a3"},{"name":"竣工图","on":0,"url":"s1a4"}]},{"id":"4801","gid":"200101","tag_name":"住宅类型","comment":["板式","塔式（一梯4户以下）","塔式（一梯5-8户）","塔式（一梯9户以上）","酒店式公寓"],"select_name":"建筑方案图","sort":"2","selects":[{"name":"板式","on":0,"url":"s1b1"},{"name":"塔式（一梯4户以下）","on":0,"url":"s1b2"},{"name":"塔式（一梯5-8户）","on":0,"url":"s1b3"},{"name":"塔式（一梯9户以上）","on":0,"url":"s1b4"},{"name":"酒店式公寓","on":0,"url":"s1b5"}]},{"id":"4807","gid":"200101","tag_name":"高度类别","comment":["单层建筑","多层建筑","高层建筑","超高层建筑"],"select_name":"建筑方案图","sort":"3","selects":[{"name":"单层建筑","on":0,"url":"s1d1"},{"name":"多层建筑","on":0,"url":"s1d2"},{"name":"高层建筑","on":0,"url":"s1d3"},{"name":"超高层建筑","on":0,"url":"s1d4"}]},{"id":"4810","gid":"200101","tag_name":"设计风格","comment":["欧陆风格","北美风格","东南亚风格","中式风格","现代风格","哈佛红","新中式风格"],"select_name":"建筑方案图","sort":"4","selects":[{"name":"欧陆风格","on":0,"url":"s1e1"},{"name":"北美风格","on":0,"url":"s1e2"},{"name":"东南亚风格","on":0,"url":"s1e3"},{"name":"中式风格","on":0,"url":"s1e4"},{"name":"现代风格","on":0,"url":"s1e5"},{"name":"哈佛红","on":0,"url":"s1e6"},{"name":"新中式风格","on":0,"url":"s1e7"}]},{"id":"4825","gid":"200101","tag_name":"图纸格式","comment":["JPG","天正7","CAD2000","PDF","PPT","DOC","XLS"],"select_name":"建筑方案图","sort":"5","selects":[{"name":"JPG","on":0,"url":"s1j1"},{"name":"天正7","on":0,"url":"s1j2"},{"name":"CAD2000","on":0,"url":"s1j3"},{"name":"PDF","on":0,"url":"s1j4"},{"name":"PPT","on":0,"url":"s1j5"},{"name":"DOC","on":0,"url":"s1j6"},{"name":"XLS","on":0,"url":"s1j7"}]},{"id":"4804","gid":"200101","tag_name":"别墅类型","comment":["独栋别墅","双拼别墅","联排别墅","叠拼别墅","小康农居"],"select_name":"建筑方案图","sort":"0","selects":[{"name":"独栋别墅","on":0,"url":"s1c1"},{"name":"双拼别墅","on":0,"url":"s1c2"},{"name":"联排别墅","on":0,"url":"s1c3"},{"name":"叠拼别墅","on":0,"url":"s1c4"},{"name":"小康农居","on":0,"url":"s1c5"}]},{"id":"4813","gid":"200101","tag_name":"设计流派","comment":{"0":"artdeco","2":"新中式","3":"新古典","4":"现代","5":"后现代","6":"解构","7":"其他"},"select_name":"建筑方案图","sort":"0","selects":[{"name":"artdeco","on":0,"url":"s1f1"},{"name":"新中式","on":0,"url":"s1f3"},{"name":"新古典","on":0,"url":"s1f4"},{"name":"现代","on":0,"url":"s1f5"},{"name":"后现代","on":0,"url":"s1f6"},{"name":"解构","on":0,"url":"s1f7"},{"name":"其他","on":0,"url":"s1f8"}]},{"id":"4816","gid":"200101","tag_name":"外立面材料","comment":["涂料","幕墙","石材","金属板材","墙砖","其他"],"select_name":"建筑方案图","sort":"0","selects":[{"name":"涂料","on":0,"url":"s1g1"},{"name":"幕墙","on":0,"url":"s1g2"},{"name":"石材","on":0,"url":"s1g3"},{"name":"金属板材","on":0,"url":"s1g4"},{"name":"墙砖","on":0,"url":"s1g5"},{"name":"其他","on":0,"url":"s1g6"}]},{"id":"4819","gid":"200101","tag_name":"结构形式","comment":["木结构","砌体结构","钢筋混凝土结构","钢结构","混合结构","其他结构"],"select_name":"建筑方案图","sort":"0","selects":[{"name":"木结构","on":0,"url":"s1h1"},{"name":"砌体结构","on":0,"url":"s1h2"},{"name":"钢筋混凝土结构","on":0,"url":"s1h3"},{"name":"钢结构","on":0,"url":"s1h4"},{"name":"混合结构","on":0,"url":"s1h5"},{"name":"其他结构","on":0,"url":"s1h6"}]},{"id":"4822","gid":"200101","tag_name":"项目位置","comment":{"0":"安徽","1":"北京","2":"重庆","3":"福建","4":"甘肃","5":"广东","6":"广西","7":"贵州","8":"海南","9":"河北","10":"河南","11":"黑龙江","12":"湖北","13":"湖南","14":"吉林","15":"江苏","16":"江西","17":"辽宁","18":"内蒙古","19":"宁夏","20":"青海","21":"山东","22":"山西","23":"陕西","24":"上海","25":"四川","26":"天津","28":"新疆","29":"云南","30":"浙江","32":"香港","34":"国外"},"select_name":"建筑方案图","sort":"0","selects":[{"name":"安徽","on":0,"url":"s1i1"},{"name":"北京","on":0,"url":"s1i2"},{"name":"重庆","on":0,"url":"s1i3"},{"name":"福建","on":0,"url":"s1i4"},{"name":"甘肃","on":0,"url":"s1i5"},{"name":"广东","on":0,"url":"s1i6"},{"name":"广西","on":0,"url":"s1i7"},{"name":"贵州","on":0,"url":"s1i8"},{"name":"海南","on":0,"url":"s1i9"},{"name":"河北","on":0,"url":"s1i10"},{"name":"河南","on":0,"url":"s1i11"},{"name":"黑龙江","on":0,"url":"s1i12"},{"name":"湖北","on":0,"url":"s1i13"},{"name":"湖南","on":0,"url":"s1i14"},{"name":"吉林","on":0,"url":"s1i15"},{"name":"江苏","on":0,"url":"s1i16"},{"name":"江西","on":0,"url":"s1i17"},{"name":"辽宁","on":0,"url":"s1i18"},{"name":"内蒙古","on":0,"url":"s1i19"},{"name":"宁夏","on":0,"url":"s1i20"},{"name":"青海","on":0,"url":"s1i21"},{"name":"山东","on":0,"url":"s1i22"},{"name":"山西","on":0,"url":"s1i23"},{"name":"陕西","on":0,"url":"s1i24"},{"name":"上海","on":0,"url":"s1i25"},{"name":"四川","on":0,"url":"s1i26"},{"name":"天津","on":0,"url":"s1i27"},{"name":"新疆","on":0,"url":"s1i29"},{"name":"云南","on":0,"url":"s1i30"},{"name":"浙江","on":0,"url":"s1i31"},{"name":"香港","on":0,"url":"s1i33"},{"name":"国外","on":0,"url":"s1i35"}]},{"tag_name":"等 级","select_name":"三星","selects":[{"tagid":3,"name":"三星","on":0,"url":"star3"},{"tagid":4,"name":"四星","on":0,"url":"star4"},{"tagid":5,"name":"五星","on":0,"url":"star5"},{"tagid":6,"name":"六星","on":0,"url":"star6"},{"tagid":7,"name":"七星","on":0,"url":"star7"}]}]
     * have_tag : 1
     * have_vip : 1
     */

    private Group groupinfo;
    private int have_tag;
    private int have_vip;
    private ArrayList<Thread> thread_list;
    private List<Tag> tag_arr;

    public Group getGroupinfo() {
        return groupinfo;
    }

    public void setGroupinfo(Group groupinfo) {
        this.groupinfo = groupinfo;
    }

    public int getHave_tag() {
        return have_tag;
    }

    public void setHave_tag(int have_tag) {
        this.have_tag = have_tag;
    }

    public int getHave_vip() {
        return have_vip;
    }

    public void setHave_vip(int have_vip) {
        this.have_vip = have_vip;
    }

    public ArrayList<Thread> getThread_list() {
        return thread_list;
    }

    public void setThread_list(ArrayList<Thread> thread_list) {
        this.thread_list = thread_list;
    }

    public List<Tag> getTag_arr() {
        return tag_arr;
    }

    public void setTag_arr(List<Tag> tag_arr) {
        this.tag_arr = tag_arr;
    }

    public static class Group{
        /**
         * gid : 200101
         * group_name : 居住建筑
         * fup : 2
         * fid : 3
         * private : 0
         * founder_uid : 10215338
         * founder_name : 菊爽爽
         * introduce : 本小组汇集住宅建筑及别墅建筑的方案文本、方案图及施工图。建筑师朋友们可以自由分享与下载独栋别墅、高层住宅、多层住宅、高层住宅楼、住宅区规划、塔式住宅、板式住宅、公寓及宿舍楼、底商住宅、联排别墅、双拼别墅、新农村自建房等的方案文本、方案图及施工图，我分享我快乐！
         * apply_word : 居住建筑
         * tag :
         * create_time : 1465205975
         * thread_balance : 10
         * post_balance : 5
         * day_balance : 5000
         * day_credit : 500
         * mon_balance : 100000
         * mon_credit : 100000
         * is_specialty : 1
         * level : 1
         * is_authority : 1
         * is_verification : 0
         * is_vip : 0
         * is_auth : 0
         * phone : 13146544654
         * qq :
         * status : 1
         * assessor_uid : 7170569
         * assessor_name : zl320
         * assessor_ip : 127.0.0.1
         * is_vip_download : 0
         * is_strangeradd : 1
         * special : 2
         * is_downreply : 0
         * star_coin :
         * group_hot : 0
         * member_num : 135329
         * thread_num : 8653
         * post_num : 14626
         * balance_num : 3300
         * credit_num : 8
         * new_member : 120
         * essence_num : 279
         * new_apply : 0
         * month_hot : 10515
         * sum_hot : 359831
         * logo : https://avatar.zhulong.com/group/000/20/01/01_logo_big.jpg
         * dateline : 2016-06-06
         * is_add : 1
         */

        private String gid;
        private String group_name;
        private String fup;
        private String fid;
        @SerializedName("private")
        private String privateX;
        private String founder_uid;
        private String founder_name;
        private String introduce;
        private String apply_word;
        private String tag;
        private String create_time;
        private String thread_balance;
        private String post_balance;
        private String day_balance;
        private String day_credit;
        private String mon_balance;
        private String mon_credit;
        private String is_specialty;
        private String level;
        private String is_authority;
        private String is_verification;
        private String is_vip;
        private String is_auth;
        private String phone;
        private String qq;
        private String status;
        private String assessor_uid;
        private String assessor_name;
        private String assessor_ip;
        private String is_vip_download;
        private String is_strangeradd;
        private String special;
        private String is_downreply;
        private String star_coin;
        private String group_hot;
        private String member_num;
        private String thread_num;
        private String post_num;
        private String balance_num;
        private String credit_num;
        private String new_member;
        private String essence_num;
        private String new_apply;
        private String month_hot;
        private String sum_hot;
        private String logo;
        private String dateline;
        private int is_add;

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getFup() {
            return fup;
        }

        public void setFup(String fup) {
            this.fup = fup;
        }

        public String getFid() {
            return fid;
        }

        public void setFid(String fid) {
            this.fid = fid;
        }

        public String getPrivateX() {
            return privateX;
        }

        public void setPrivateX(String privateX) {
            this.privateX = privateX;
        }

        public String getFounder_uid() {
            return founder_uid;
        }

        public void setFounder_uid(String founder_uid) {
            this.founder_uid = founder_uid;
        }

        public String getFounder_name() {
            return founder_name;
        }

        public void setFounder_name(String founder_name) {
            this.founder_name = founder_name;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getApply_word() {
            return apply_word;
        }

        public void setApply_word(String apply_word) {
            this.apply_word = apply_word;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getThread_balance() {
            return thread_balance;
        }

        public void setThread_balance(String thread_balance) {
            this.thread_balance = thread_balance;
        }

        public String getPost_balance() {
            return post_balance;
        }

        public void setPost_balance(String post_balance) {
            this.post_balance = post_balance;
        }

        public String getDay_balance() {
            return day_balance;
        }

        public void setDay_balance(String day_balance) {
            this.day_balance = day_balance;
        }

        public String getDay_credit() {
            return day_credit;
        }

        public void setDay_credit(String day_credit) {
            this.day_credit = day_credit;
        }

        public String getMon_balance() {
            return mon_balance;
        }

        public void setMon_balance(String mon_balance) {
            this.mon_balance = mon_balance;
        }

        public String getMon_credit() {
            return mon_credit;
        }

        public void setMon_credit(String mon_credit) {
            this.mon_credit = mon_credit;
        }

        public String getIs_specialty() {
            return is_specialty;
        }

        public void setIs_specialty(String is_specialty) {
            this.is_specialty = is_specialty;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getIs_authority() {
            return is_authority;
        }

        public void setIs_authority(String is_authority) {
            this.is_authority = is_authority;
        }

        public String getIs_verification() {
            return is_verification;
        }

        public void setIs_verification(String is_verification) {
            this.is_verification = is_verification;
        }

        public String getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(String is_vip) {
            this.is_vip = is_vip;
        }

        public String getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(String is_auth) {
            this.is_auth = is_auth;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAssessor_uid() {
            return assessor_uid;
        }

        public void setAssessor_uid(String assessor_uid) {
            this.assessor_uid = assessor_uid;
        }

        public String getAssessor_name() {
            return assessor_name;
        }

        public void setAssessor_name(String assessor_name) {
            this.assessor_name = assessor_name;
        }

        public String getAssessor_ip() {
            return assessor_ip;
        }

        public void setAssessor_ip(String assessor_ip) {
            this.assessor_ip = assessor_ip;
        }

        public String getIs_vip_download() {
            return is_vip_download;
        }

        public void setIs_vip_download(String is_vip_download) {
            this.is_vip_download = is_vip_download;
        }

        public String getIs_strangeradd() {
            return is_strangeradd;
        }

        public void setIs_strangeradd(String is_strangeradd) {
            this.is_strangeradd = is_strangeradd;
        }

        public String getSpecial() {
            return special;
        }

        public void setSpecial(String special) {
            this.special = special;
        }

        public String getIs_downreply() {
            return is_downreply;
        }

        public void setIs_downreply(String is_downreply) {
            this.is_downreply = is_downreply;
        }

        public String getStar_coin() {
            return star_coin;
        }

        public void setStar_coin(String star_coin) {
            this.star_coin = star_coin;
        }

        public String getGroup_hot() {
            return group_hot;
        }

        public void setGroup_hot(String group_hot) {
            this.group_hot = group_hot;
        }

        public String getMember_num() {
            return member_num;
        }

        public void setMember_num(String member_num) {
            this.member_num = member_num;
        }

        public String getThread_num() {
            return thread_num;
        }

        public void setThread_num(String thread_num) {
            this.thread_num = thread_num;
        }

        public String getPost_num() {
            return post_num;
        }

        public void setPost_num(String post_num) {
            this.post_num = post_num;
        }

        public String getBalance_num() {
            return balance_num;
        }

        public void setBalance_num(String balance_num) {
            this.balance_num = balance_num;
        }

        public String getCredit_num() {
            return credit_num;
        }

        public void setCredit_num(String credit_num) {
            this.credit_num = credit_num;
        }

        public String getNew_member() {
            return new_member;
        }

        public void setNew_member(String new_member) {
            this.new_member = new_member;
        }

        public String getEssence_num() {
            return essence_num;
        }

        public void setEssence_num(String essence_num) {
            this.essence_num = essence_num;
        }

        public String getNew_apply() {
            return new_apply;
        }

        public void setNew_apply(String new_apply) {
            this.new_apply = new_apply;
        }

        public String getMonth_hot() {
            return month_hot;
        }

        public void setMonth_hot(String month_hot) {
            this.month_hot = month_hot;
        }

        public String getSum_hot() {
            return sum_hot;
        }

        public void setSum_hot(String sum_hot) {
            this.sum_hot = sum_hot;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public int getIs_add() {
            return is_add;
        }

        public void setIs_add(int is_add) {
            this.is_add = is_add;
        }

    }

    public static class Thread implements Parcelable {
        /**
         * tid : 20094205
         * gid : 200101
         * title : 河南某二十四层住宅楼建筑结构施工图
         * pic : http://newoss.zhulong.com/tfs/pic/v1/tfs/T1aLY_ByWv1RCvBVdK.jpg
         * create_time : 1126226340
         * view_num : 4310
         * reply_num : 1
         * username : 常正非
         * uid : 333019
         * is_essence : 1
         * user_img : http://avatar.zhulong.com/avatar/000/33/30/19_avatar_small.jpg
         */

        private String tid;
        private String gid;
        private String title;
        private String pic;
        private String create_time;
        private String view_num;
        private String reply_num;
        private String username;
        private String uid;
        private String is_essence;
        private String user_img;

        protected Thread(Parcel in) {
            tid = in.readString();
            gid = in.readString();
            title = in.readString();
            pic = in.readString();
            create_time = in.readString();
            view_num = in.readString();
            reply_num = in.readString();
            username = in.readString();
            uid = in.readString();
            is_essence = in.readString();
            user_img = in.readString();
        }

        public static final Creator<Thread> CREATOR = new Creator<Thread>() {
            @Override
            public Thread createFromParcel(Parcel in) {
                return new Thread(in);
            }

            @Override
            public Thread[] newArray(int size) {
                return new Thread[size];
            }
        };

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getView_num() {
            return view_num;
        }

        public void setView_num(String view_num) {
            this.view_num = view_num;
        }

        public String getReply_num() {
            return reply_num;
        }

        public void setReply_num(String reply_num) {
            this.reply_num = reply_num;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getIs_essence() {
            return is_essence;
        }

        public void setIs_essence(String is_essence) {
            this.is_essence = is_essence;
        }

        public String getUser_img() {
            return user_img;
        }

        public void setUser_img(String user_img) {
            this.user_img = user_img;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(tid);
            dest.writeString(gid);
            dest.writeString(title);
            dest.writeString(pic);
            dest.writeString(create_time);
            dest.writeString(view_num);
            dest.writeString(reply_num);
            dest.writeString(username);
            dest.writeString(uid);
            dest.writeString(is_essence);
            dest.writeString(user_img);
        }
    }

    public static class Tag{
        /**
         * tag_name : 资料属性
         * select_name : 建筑方案图
         * on : 1
         * selects : [{"on":1,"name":"建筑方案图","url":"s1"},{"on":0,"name":"建筑方案文本","url":"s2"},{"on":0,"name":"建筑施工图","url":"s3"}]
         * id : 4798
         * gid : 200101
         * comment : ["方案（初设图）","扩初图","施工图","竣工图"]
         * sort : 1
         */

        private String tag_name;
        private String select_name;
        private int on;
        private String id;
        private String gid;
        private String sort;
        private List<SelectsBean> selects;
        private List<String> containsName;

        public List<String> getContainsName() {
            return containsName;
        }

        public void setContainsName(List<String> pContainsName) {
            containsName = pContainsName;
        }

        //        private List<String> comment;
        private boolean selecting;

        public boolean isSelecting() {
            return selecting;
        }

        public void setSelecting(boolean selecting) {
            this.selecting = selecting;
        }

        public String getTag_name() {
            return tag_name;
        }

        public void setTag_name(String tag_name) {
            this.tag_name = tag_name;
        }

        public String getSelect_name() {
            return select_name;
        }

        public void setSelect_name(String select_name) {
            this.select_name = select_name;
        }

        public int getOn() {
            return on;
        }

        public void setOn(int on) {
            this.on = on;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public List<SelectsBean> getSelects() {
            return selects;
        }

        public void setSelects(List<SelectsBean> selects) {
            this.selects = selects;
        }
//
//        public List<String> getComment() {
////            return comment;
////        }
////
////        public void setComment(List<String> comment) {
////            this.comment = comment;
////        }


        public static class SelectsBean {
            /**
             * on : 1
             * name : 建筑方案图
             * url : s1
             */

            private int on;
            private String name;
            private String url;

            public int getOn() {
                return on;
            }

            public void setOn(int on) {
                this.on = on;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
