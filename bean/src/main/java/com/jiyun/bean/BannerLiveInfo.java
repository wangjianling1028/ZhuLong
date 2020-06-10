package com.jiyun.bean;


import java.io.Serializable;
import java.util.List;

public class BannerLiveInfo implements Serializable {
    private static final long serialVersionUID = -5079196530172264541L;
    public List<Carousel> Carousel;
    public List<Live> live;

    public class Carousel implements Serializable {
        public String img;
        public String thumb;
        public String url;
    }

    public class Live implements Serializable {
        public Live(String pActivityName) {
            activityName = pActivityName;
        }

        public String activityName;
        public String correlative_lessons;
        public String coverImgUrl;
        public String endTime;
        public String from_type;
        public String is_liveing;
        public String lesson_id;
        public String live_id;
        public String specialty_id;
        public String startTime;
        public String teacher_name;
        public String teacher_photo;
        public String teacher_uid;
        public String url;
    }
}
