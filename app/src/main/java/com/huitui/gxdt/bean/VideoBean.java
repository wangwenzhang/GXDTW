package com.huitui.gxdt.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wangwenzhang on 2016/11/14.
 */
@DatabaseTable(tableName = "vidio")
public class VideoBean {
    @DatabaseField(columnName = "_id", generatedId = true)
    private long id;
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "videoURL")
    private String videoURL;
    @DatabaseField(columnName = "resoure")
    private String resoure;
    @DatabaseField(columnName = "thumbnail")
    private String thumbnail;
    public VideoBean(){}
    public VideoBean(String title, String videoURL, String resoure, String thumbnail) {
        this.title = title;
        this.videoURL = videoURL;
        this.resoure = resoure;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getResoure() {
        return resoure;
    }

    public void setResoure(String resoure) {
        this.resoure = resoure;
    }


}
