package com.jiyun.frame.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xxl on 2017/11/21.
 * 专业切换选择实体
 */

public class SpecialtyBean implements Serializable {

    private static final long serialVersionUID = 7871654366992630138L;
    /**
     * errNo : 0
     * result : [{"bigspecialty":"设计学院","is_classify":1,"icon":"http://edu.zhulong.com/img/classicon/sheji.png","data":[{"specialty_id":"1","fid":3,"specialty_name":"建筑设计"},{"specialty_id":"2","fid":5,"specialty_name":"室内设计"},{"specialty_id":"3","fid":4,"specialty_name":"园林景观"}]},{"bigspecialty":"工程学院","is_classify":1,"icon":"http://edu.zhulong.com/img/classicon/gongcheng.png","data":[{"specialty_id":"15","fid":12,"specialty_name":"结构设计"},{"specialty_id":"5","fid":8,"specialty_name":"建筑施工"},{"specialty_id":"9","fid":9,"specialty_name":"路桥市政"},{"specialty_id":"22","fid":10,"specialty_name":"岩土工程"}]},{"bigspecialty":"机电学院","is_classify":1,"icon":"http://edu.zhulong.com/img/classicon/jidian.png","data":[{"specialty_id":"8","fid":21,"specialty_name":"电气工程"},{"specialty_id":"7","fid":19,"specialty_name":"给水排水"},{"specialty_id":"6","fid":20,"specialty_name":"暖通空调"}]},{"bigspecialty":"造价学院","is_classify":1,"icon":"http://edu.zhulong.com/img/classicon/zaojia.png","data":[{"specialty_id":"4","fid":14,"specialty_name":"工程造价"},{"specialty_id":"12","fid":17,"specialty_name":"房地产"},{"specialty_id":"11","fid":34,"specialty_name":"施工监理"},{"specialty_id":"10","fid":16,"specialty_name":"项目管理"}]},{"bigspecialty":"BIM学院","is_classify":0,"icon":"http://edu.zhulong.com/img/classicon/bim.png","data":[{"specialty_id":"21","fid":29,"specialty_name":"BIM技术"}]}]
     * extra : [{"specialty_id":0,"fid":47,"specialty_name":"精品课"},{"specialty_id":0,"fid":27,"specialty_name":"训练营"},{"specialty_id":0,"fid":23,"specialty_name":"注册考试"},{"specialty_id":0,"fid":43,"specialty_name":"站务讨论"}]
     * exeTime : 0
     */

    private int errNo;
    private int exeTime;
    private List<ResultBean> result;
    private List<ExtraBean> extra;

    @Override
    public String toString() {
        return "SpecialtyBean{" +
                "errNo=" + errNo +
                ", exeTime=" + exeTime +
                ", result=" + result +
                ", extra=" + extra +
                '}';
    }

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

    public List<ExtraBean> getExtra() {
        return extra;
    }

    public void setExtra(List<ExtraBean> extra) {
        this.extra = extra;
    }

    public static class ResultBean implements Serializable {
        /**
         * bigspecialty : 设计学院
         * is_classify : 1
         * icon : http://edu.zhulong.com/img/classicon/sheji.png
         * data : [{"specialty_id":"1","fid":3,"specialty_name":"建筑设计"},{"specialty_id":"2","fid":5,"specialty_name":"室内设计"},{"specialty_id":"3","fid":4,"specialty_name":"园林景观"}]
         */

        private String bigspecialty;
        private int is_classify;
        private String icon;
        private List<DataBean> data;

        @Override
        public String toString() {
            return "ResultBean{" +
                    "bigspecialty='" + bigspecialty + '\'' +
                    ", is_classify=" + is_classify +
                    ", icon='" + icon + '\'' +
                    ", data=" + data +
                    '}';
        }

        public String getBigspecialty() {
            return bigspecialty;
        }

        public void setBigspecialty(String bigspecialty) {
            this.bigspecialty = bigspecialty;
        }

        public int getIs_classify() {
            return is_classify;
        }

        public void setIs_classify(int is_classify) {
            this.is_classify = is_classify;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable {
            /**
             * specialty_id : 1
             * fid : 3
             * specialty_name : 建筑设计
             */

            private String specialty_id;
            private int fid;
            private String specialty_name;
            private boolean isSelected;

            @Override
            public String toString() {
                return "DataBean{" +
                        "specialty_id='" + specialty_id + '\'' +
                        ", fid=" + fid +
                        ", specialty_name='" + specialty_name + '\'' +
                        ", isSelected=" + isSelected +
                        '}';
            }

            public boolean isSelected() {
                return isSelected;
            }

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public String getSpecialty_id() {
                return specialty_id;
            }

            public void setSpecialty_id(String specialty_id) {
                this.specialty_id = specialty_id;
            }

            public int getFid() {
                return fid;
            }

            public void setFid(int fid) {
                this.fid = fid;
            }

            public String getSpecialty_name() {
                return specialty_name;
            }

            public void setSpecialty_name(String specialty_name) {
                this.specialty_name = specialty_name;
            }
        }
    }

    public static class ExtraBean {
        /**
         * specialty_id : 0
         * fid : 47
         * specialty_name : 精品课
         */

        private int specialty_id;
        private int fid;
        private String specialty_name;

        @Override
        public String toString() {
            return "ExtraBean{" +
                    "specialty_id=" + specialty_id +
                    ", fid=" + fid +
                    ", specialty_name='" + specialty_name + '\'' +
                    '}';
        }

        public int getSpecialty_id() {
            return specialty_id;
        }

        public void setSpecialty_id(int specialty_id) {
            this.specialty_id = specialty_id;
        }

        public int getFid() {
            return fid;
        }

        public void setFid(int fid) {
            this.fid = fid;
        }

        public String getSpecialty_name() {
            return specialty_name;
        }

        public void setSpecialty_name(String specialty_name) {
            this.specialty_name = specialty_name;
        }
    }
}
