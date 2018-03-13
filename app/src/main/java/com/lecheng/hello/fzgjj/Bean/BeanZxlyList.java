package com.lecheng.hello.fzgjj.Bean;

import java.util.List;

/**
 * Created by 乐城 on 2017/5/25.
 */

public class BeanZxlyList {
    /**
     * data : [{"createdate":"2017-04-01 00:00:00.0","hfnr":"54984189549841895498418954984189554984189549841895498418954984189549841895498418954984189549841895498418954984189549841895498418954984189498418954984189549841895498418954984189549841895498418954984189","name":"张龙猫","title":"张龙猫","twnr":"张龙猫","type":"1","updatedate":"2017-04-14 16:00:15.0"},{"createdate":"2017-04-05 00:00:00.0","hfnr":"123","name":"张龙猫","title":"张龙猫","twnr":"张龙猫","type":"2","updatedate":"2017-04-25 14:11:16.0"},{"createdate":"2017-04-01 00:00:00.0","name":"张龙猫","title":"张龙猫","twnr":"张龙猫","type":"3"},{"createdate":"2017-04-01 00:00:00.0","name":"张龙猫","title":"张龙猫","twnr":"张龙猫","type":"2","updatedate":"2017-05-23 10:42:21.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"··","name":"张龙猫","title":"张龙猫","twnr":"张龙猫","type":"2","updatedate":"2017-05-23 10:41:59.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待\n   因为您的支取数额巨大，请耐心等待","name":"礼貌","title":"支取相关问题","twnr":"为什么支取速度这么慢？","type":"1","updatedate":"2017-05-23 10:41:59.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待   因为您的支取数额巨大，请耐心等待","name":"礼貌","title":"支取相关问题3","twnr":"为什么支取速度这么慢？3","type":"1","updatedate":"2017-05-23 10:41:59.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待   因为您的支取数额巨大，请耐心等待","name":"礼貌","title":"支取相关问题4","twnr":"为什么支取速度这么慢？4","type":"1","updatedate":"2017-05-23 10:41:59.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待   因为您的支取数额巨大，请耐心等待","name":"礼貌","title":"支取相关问题5","twnr":"为什么支取速度这么慢？5","type":"1","updatedate":"2017-05-23 10:41:59.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待   因为您的支取数额巨大，请耐心等待","name":"礼貌","title":"支取相关问题6","twnr":"为什么支取速度这么慢？6","type":"1","updatedate":"2017-05-23 10:41:59.0"}]
     * status : 1
     */

    private String status;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createdate : 2017-04-01 00:00:00.0
         * hfnr : 54984189549841895498418954984189554984189549841895498418954984189549841895498418954984189549841895498418954984189549841895498418954984189498418954984189549841895498418954984189549841895498418954984189
         * name : 张龙猫
         * title : 张龙猫
         * twnr : 张龙猫
         * type : 1
         * updatedate : 2017-04-14 16:00:15.0
         */

        private String createdate;
        private String hfnr;
        private String name;
        private String title;
        private String twnr;
        private String type;
        private String updatedate;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        private String status;

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public String getHfnr() {
            return hfnr;
        }

        public void setHfnr(String hfnr) {
            this.hfnr = hfnr;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTwnr() {
            return twnr;
        }

        public void setTwnr(String twnr) {
            this.twnr = twnr;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdatedate() {
            return updatedate;
        }

        public void setUpdatedate(String updatedate) {
            this.updatedate = updatedate;
        }
    }
    /* {"data":[{"createdate":"2017-04-01 00:00:00.0","hfnr":"54984189549841895498418954984189554984189549841895498418954984189549841895498418954984189549841895498418954984189549841895498418954984189498418954984189549841895498418954984189549841895498418954984189","name":"张龙猫","title":"张龙猫","twnr":"张龙猫","type":"1","updatedate":"2017-04-14 16:00:15.0"},{"createdate":"2017-04-05 00:00:00.0","hfnr":"123","name":"张龙猫","title":"张龙猫","twnr":"张龙猫","type":"2","updatedate":"2017-04-25 14:11:16.0"},{"createdate":"2017-04-01 00:00:00.0","name":"张龙猫","title":"张龙猫","twnr":"张龙猫","type":"3"},{"createdate":"2017-04-01 00:00:00.0","name":"张龙猫","title":"张龙猫","twnr":"张龙猫","type":"2","updatedate":"2017-05-23 10:42:21.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"··","name":"张龙猫","title":"张龙猫","twnr":"张龙猫","type":"2","updatedate":"2017-05-23 10:41:59.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待\n   因为您的支取数额巨大，请耐心等待","name":"礼貌","title":"支取相关问题","twnr":"为什么支取速度这么慢？","type":"1","updatedate":"2017-05-23 10:41:59.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待   因为您的支取数额巨大，请耐心等待","name":"礼貌","title":"支取相关问题3","twnr":"为什么支取速度这么慢？3","type":"1","updatedate":"2017-05-23 10:41:59.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待   因为您的支取数额巨大，请耐心等待","name":"礼貌","title":"支取相关问题4","twnr":"为什么支取速度这么慢？4","type":"1","updatedate":"2017-05-23 10:41:59.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待   因为您的支取数额巨大，请耐心等待","name":"礼貌","title":"支取相关问题5","twnr":"为什么支取速度这么慢？5","type":"1","updatedate":"2017-05-23 10:41:59.0"},{"createdate":"2017-04-02 00:00:00.0","hfnr":"因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待因为您的支取数额巨大，请耐心等待   因为您的支取数额巨大，请耐心等待","name":"礼貌","title":"支取相关问题6","twnr":"为什么支取速度这么慢？6","type":"1","updatedate":"2017-05-23 10:41:59.0"}],"status":"1"}*/

}
