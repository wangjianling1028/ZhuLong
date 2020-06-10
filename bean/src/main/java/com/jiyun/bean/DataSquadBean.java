package com.jiyun.bean;

import java.util.List;

public class DataSquadBean {
    /**
     * errNo : 0
     * result : [{"group_name":"BIM软件技巧","gid":"3002227","member_num":"31877","introduce":"bim软件技巧小组每日滚动更新，为广大BIMer提供软件安装包、下载方法、系统操作技巧及常见问题解决方法，拓展新技能,bim菜鸟变达人。","avatar":"https://avatar.zhulong.com/group/003/00/22/27_logo_middle.jpg","is_ftop":1},{"group_name":"BIM族库","gid":"3002230","member_num":"31458","introduce":"bim族库小组为广大BIM设计工程师打造可视化、参数化的Revit族库下载、案例模型、管理构件平台，海量族库资源让你的学习更高效。","avatar":"https://avatar.zhulong.com/group/003/00/22/30_logo_middle.jpg","is_ftop":1},{"group_name":"BIM案例","gid":"3002233","member_num":"31149","introduce":"bim案例小组为设计企业及个人提供BIM实施标准下的具体方法和实践内容,师从前辈，专业的案例分享给专业的你。","avatar":"https://avatar.zhulong.com/group/003/00/22/33_logo_middle.jpg","is_ftop":1},{"group_name":"BIM培训讲义","gid":"3002337","member_num":"23247","introduce":"BIM培训讲义小组为设计企业及个人提供各种BIM相关培训讲义，师从前辈，专业的讲义分享给专业的你。","avatar":"https://avatar.zhulong.com/group/003/00/23/37_logo_middle.jpg","is_ftop":1},{"group_name":"BIM论文","gid":"3002341","member_num":"22131","introduce":"BIM论文小组为设计企业及个人提供BIM相关专业论文，师从前辈，专业的案例分享给专业的你。","avatar":"https://avatar.zhulong.com/group/003/00/23/41_logo_middle.jpg","is_ftop":1},{"group_name":"其他BIM资料","gid":"3002343","member_num":"22295","introduce":"其他BIM资料小组为设计企业及个人提供BIM相关资料,师从前辈，专业的案例分享给专业的你。","avatar":"https://avatar.zhulong.com/group/003/00/23/43_logo_middle.jpg","is_ftop":1}]
     * exeTime : 0
     */

    private int errNo;
    private int exeTime;
    private List<ResultBean> result;

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    public int getExeTime() {
        return exeTime;
    }

    public void setExeTime(int exeTime) {
        this.exeTime = exeTime;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * group_name : BIM软件技巧
         * gid : 3002227
         * member_num : 31877
         * introduce : bim软件技巧小组每日滚动更新，为广大BIMer提供软件安装包、下载方法、系统操作技巧及常见问题解决方法，拓展新技能,bim菜鸟变达人。
         * avatar : https://avatar.zhulong.com/group/003/00/22/27_logo_middle.jpg
         * is_ftop : 1
         */

        private String group_name;
        private String gid;
        private String member_num;
        private String introduce;
        private String avatar;
        private int is_ftop;

        public String getGroup_name() {
            return group_name;
        }

        public void setGroup_name(String group_name) {
            this.group_name = group_name;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }

        public String getMember_num() {
            return member_num;
        }

        public void setMember_num(String member_num) {
            this.member_num = member_num;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getIs_ftop() {
            return is_ftop;
        }
        public boolean isFocus(){
            return getIs_ftop() == 1 ? true : false;
        }


        public void setIs_ftop(int is_ftop) {
            this.is_ftop = is_ftop;
        }
    }
}
