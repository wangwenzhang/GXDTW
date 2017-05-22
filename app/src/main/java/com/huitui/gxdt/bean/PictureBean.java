package com.huitui.gxdt.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by wangwenzhang on 2016/11/14.
 */
@DatabaseTable(tableName = "picture")
public class PictureBean {
    @DatabaseField(columnName = "_id", generatedId = true)
    private long id;
    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    @DatabaseField(columnName = "ID")
    private int ID;
    @DatabaseField(columnName = "title")
    private String title;
    @DatabaseField(columnName = "classname")
    private String classname;
    @DatabaseField(columnName = "resource")
    private String resource;
    @DatabaseField(columnName = "chick")
    private boolean chick;

    public boolean isChick() {
        return chick;
    }

    public void setChick(boolean chick) {
        this.chick = chick;
    }

    public PictureBean(){}
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public PictureBean(int ID, String title, String classname, String resource,boolean chick) {
        this.ID = ID;
        this.title = title;
        this.classname = classname;
        this.resource = resource;
        this.chick=chick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }


}
