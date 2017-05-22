package com.huitui.gxdt.bean;

/**
 * Created by wangwenzhang on 2016/12/8.
 */
public class UpdateBean {

    /**
     * code : 0
     * msg : success
     * result : {"version":"1.0",
     * "link":"www.baidu.com"
     * }
     */

    private int code;
    private String msg;
    /**
     * version : v1
     */

    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        private String version;
        private String link;

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getVersion() {
            return version;

        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
