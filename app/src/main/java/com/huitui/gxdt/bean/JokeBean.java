package com.huitui.gxdt.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wangwenzhang on 2016/11/14.
 */
@DatabaseTable(tableName = "joke")
public class JokeBean {
    @DatabaseField(columnName = "_id", generatedId = true)
    private long id;
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    @DatabaseField(columnName = "imageID")
    private int imageID;
    @DatabaseField(columnName = "tiltle")
    private String tiltle;
    @DatabaseField(columnName = "resoure")
    private String resoure;
    @DatabaseField(columnName = "thumbnail")
    private String thumbnail;
    @DatabaseField(columnName = "classname")
    private String classname;
    @DatabaseField(columnName = "chick")
    private boolean chick;
    public boolean isChick() {
        return chick;
    }
    public void setChick(boolean chick) {
        this.chick = chick;
    }
    private JokeBean (){}
    public JokeBean(int imageID, String tiltle, String resoure, String thumbnail, String classname,boolean chick) {
        this.imageID = imageID;
        this.tiltle = tiltle;
        this.resoure = resoure;
        this.thumbnail = thumbnail;
        this.classname = classname;
        this.chick=chick;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getResoure() {
        return resoure;
    }

    public void setResoure(String resoure) {
        this.resoure = resoure;
    }

    public String getTiltle() {
        return tiltle;
    }

    public void setTiltle(String tiltle) {
        this.tiltle = tiltle;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
