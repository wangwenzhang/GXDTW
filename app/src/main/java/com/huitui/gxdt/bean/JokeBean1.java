package com.huitui.gxdt.bean;

import java.util.List;

/**
 * Created by wangwenzhang on 2016/12/13.
 */
public class JokeBean1 {

    /**
     * count : 1000
     * np : 1.481527106E9
     */

    private InfoBean info;
    /**
     * status : 4
     * comment : 32
     * top_comments : [{"voicetime":0,"status":0,"hate_count":0,"cmt_type":"text","precid":0,"content":"你还能活着来发段子，说明你女友是亲的！","like_count":31,"u":{"header":["http://wimg.spriteapp.cn/profile/large/2016/10/09/57fa59ab6dce4_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/10/09/57fa59ab6dce4_mini.jpg"],"uid":"19198465","is_vip":false,"room_url":"","sex":"m","room_name":"","room_role":"","room_icon":"","name":"猫的悲伤你不懂"},"preuid":0,"passtime":"2016-12-08 15:33:44","voiceuri":"","id":70376664},{"voicetime":0,"status":0,"hate_count":0,"cmt_type":"text","precid":0,"content":"这个弹蛋蛋的梗是过不去了是吧。","like_count":18,"u":{"header":["http://wimg.spriteapp.cn/profile/large/2016/07/19/578d65e4bf65d_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/07/19/578d65e4bf65d_mini.jpg"],"uid":"18736660","is_vip":false,"room_url":"","sex":"m","room_name":"","room_role":"","room_icon":"","name":"gytfghhh"},"preuid":0,"passtime":"2016-12-08 13:26:19","voiceuri":"","id":70369418},{"voicetime":0,"status":0,"hate_count":0,"cmt_type":"text","precid":0,"content":"你看他也有蛋蛋","like_count":14,"u":{"header":["http://app.qlogo.cn/mbloghead/16c07f73c3b6c8607336/50","http://app.qlogo.cn/mbloghead/16c07f73c3b6c8607336/50"],"uid":"19570628","is_vip":false,"room_url":"","sex":"m","room_name":"","room_role":"","room_icon":"","name":"吴吴111"},"preuid":0,"passtime":"2016-12-08 11:25:16","voiceuri":"","id":70362017}]
     * tags : [{"id":60,"name":"吐槽"},{"id":61,"name":"恶搞"},{"id":64,"name":"糗事"}]
     * bookmark : 21
     * text : 跟女友逛商场。对面坐着个美女，穿着短裙，我下意识的蹲下装作找东西偷看了一眼，被女友发现了:“你看到什么了？”我一把拉过女友蹲下:“你看，你们俩撞衫了……”
     * up : 522
     * share_url : http://a.f.budejie.com/share/22505104.html?wx.qq.com
     * down : 84
     * forward : 27
     * u : {"header":["http://wimg.spriteapp.cn/profile/large/2016/08/19/57b652a07b2c2_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/08/19/57b652a07b2c2_mini.jpg"],"uid":"19124361","is_vip":false,"is_v":false,"room_url":"","room_name":"意大利炮","room_role":"","room_icon":"http://wimg.spriteapp.cn/ugc/2016/1101/gang_level_3.png","name":"等等我们 [意大利炮]"}
     * passtime : 2016-12-13 09:56:01
     * type : text
     * id : 22505104
     */

    private List<ListBean> list;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class InfoBean {
        private int count;
        private double np;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getNp() {
            return np;
        }

        public void setNp(double np) {
            this.np = np;
        }
    }

    public static class ListBean {
        private int status;
        private String comment;
        private String bookmark;
        private String text;
        private String up;
        private String share_url;
        private int down;
        private int forward;
        /**
         * header : ["http://wimg.spriteapp.cn/profile/large/2016/08/19/57b652a07b2c2_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/08/19/57b652a07b2c2_mini.jpg"]
         * uid : 19124361
         * is_vip : false
         * is_v : false
         * room_url :
         * room_name : 意大利炮
         * room_role :
         * room_icon : http://wimg.spriteapp.cn/ugc/2016/1101/gang_level_3.png
         * name : 等等我们 [意大利炮]
         */

        private UBean u;
        private String passtime;
        private String type;
        private String id;
        /**
         * voicetime : 0
         * status : 0
         * hate_count : 0
         * cmt_type : text
         * precid : 0
         * content : 你还能活着来发段子，说明你女友是亲的！
         * like_count : 31
         * u : {"header":["http://wimg.spriteapp.cn/profile/large/2016/10/09/57fa59ab6dce4_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/10/09/57fa59ab6dce4_mini.jpg"],"uid":"19198465","is_vip":false,"room_url":"","sex":"m","room_name":"","room_role":"","room_icon":"","name":"猫的悲伤你不懂"}
         * preuid : 0
         * passtime : 2016-12-08 15:33:44
         * voiceuri :
         * id : 70376664
         */

        private List<TopCommentsBean> top_comments;
        /**
         * id : 60
         * name : 吐槽
         */

        private List<TagsBean> tags;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getBookmark() {
            return bookmark;
        }

        public void setBookmark(String bookmark) {
            this.bookmark = bookmark;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUp() {
            return up;
        }

        public void setUp(String up) {
            this.up = up;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public int getDown() {
            return down;
        }

        public void setDown(int down) {
            this.down = down;
        }

        public int getForward() {
            return forward;
        }

        public void setForward(int forward) {
            this.forward = forward;
        }

        public UBean getU() {
            return u;
        }

        public void setU(UBean u) {
            this.u = u;
        }

        public String getPasstime() {
            return passtime;
        }

        public void setPasstime(String passtime) {
            this.passtime = passtime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<TopCommentsBean> getTop_comments() {
            return top_comments;
        }

        public void setTop_comments(List<TopCommentsBean> top_comments) {
            this.top_comments = top_comments;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class UBean {
            private String uid;
            private boolean is_vip;
            private boolean is_v;
            private String room_url;
            private String room_name;
            private String room_role;
            private String room_icon;
            private String name;
            private List<String> header;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public boolean isIs_vip() {
                return is_vip;
            }

            public void setIs_vip(boolean is_vip) {
                this.is_vip = is_vip;
            }

            public boolean isIs_v() {
                return is_v;
            }

            public void setIs_v(boolean is_v) {
                this.is_v = is_v;
            }

            public String getRoom_url() {
                return room_url;
            }

            public void setRoom_url(String room_url) {
                this.room_url = room_url;
            }

            public String getRoom_name() {
                return room_name;
            }

            public void setRoom_name(String room_name) {
                this.room_name = room_name;
            }

            public String getRoom_role() {
                return room_role;
            }

            public void setRoom_role(String room_role) {
                this.room_role = room_role;
            }

            public String getRoom_icon() {
                return room_icon;
            }

            public void setRoom_icon(String room_icon) {
                this.room_icon = room_icon;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<String> getHeader() {
                return header;
            }

            public void setHeader(List<String> header) {
                this.header = header;
            }
        }

        public static class TopCommentsBean {
            private int voicetime;
            private int status;
            private int hate_count;
            private String cmt_type;
            private int precid;
            private String content;
            private int like_count;
            /**
             * header : ["http://wimg.spriteapp.cn/profile/large/2016/10/09/57fa59ab6dce4_mini.jpg","http://dimg.spriteapp.cn/profile/large/2016/10/09/57fa59ab6dce4_mini.jpg"]
             * uid : 19198465
             * is_vip : false
             * room_url :
             * sex : m
             * room_name :
             * room_role :
             * room_icon :
             * name : 猫的悲伤你不懂
             */

            private UBean u;
            private int preuid;
            private String passtime;
            private String voiceuri;
            private int id;

            public int getVoicetime() {
                return voicetime;
            }

            public void setVoicetime(int voicetime) {
                this.voicetime = voicetime;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getHate_count() {
                return hate_count;
            }

            public void setHate_count(int hate_count) {
                this.hate_count = hate_count;
            }

            public String getCmt_type() {
                return cmt_type;
            }

            public void setCmt_type(String cmt_type) {
                this.cmt_type = cmt_type;
            }

            public int getPrecid() {
                return precid;
            }

            public void setPrecid(int precid) {
                this.precid = precid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getLike_count() {
                return like_count;
            }

            public void setLike_count(int like_count) {
                this.like_count = like_count;
            }

            public UBean getU() {
                return u;
            }

            public void setU(UBean u) {
                this.u = u;
            }

            public int getPreuid() {
                return preuid;
            }

            public void setPreuid(int preuid) {
                this.preuid = preuid;
            }

            public String getPasstime() {
                return passtime;
            }

            public void setPasstime(String passtime) {
                this.passtime = passtime;
            }

            public String getVoiceuri() {
                return voiceuri;
            }

            public void setVoiceuri(String voiceuri) {
                this.voiceuri = voiceuri;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public static class UBean {
                private String uid;
                private boolean is_vip;
                private String room_url;
                private String sex;
                private String room_name;
                private String room_role;
                private String room_icon;
                private String name;
                private List<String> header;

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public boolean isIs_vip() {
                    return is_vip;
                }

                public void setIs_vip(boolean is_vip) {
                    this.is_vip = is_vip;
                }

                public String getRoom_url() {
                    return room_url;
                }

                public void setRoom_url(String room_url) {
                    this.room_url = room_url;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getRoom_name() {
                    return room_name;
                }

                public void setRoom_name(String room_name) {
                    this.room_name = room_name;
                }

                public String getRoom_role() {
                    return room_role;
                }

                public void setRoom_role(String room_role) {
                    this.room_role = room_role;
                }

                public String getRoom_icon() {
                    return room_icon;
                }

                public void setRoom_icon(String room_icon) {
                    this.room_icon = room_icon;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public List<String> getHeader() {
                    return header;
                }

                public void setHeader(List<String> header) {
                    this.header = header;
                }
            }
        }

        public static class TagsBean {
            private int id;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
