package com.huitui.gxdt.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Administrator on 2016/8/1.
 */
@DatabaseTable(tableName = "cart")
public class DataBaseBean {
    @DatabaseField(columnName = "_id", generatedId = true)
    private long id;

    public long getId() {
        return id;
    }

    @DatabaseField(columnName = "goodID")
    private int goodID;

    @DatabaseField(columnName = "title")
    private String title;

    @DatabaseField(columnName = "imgUrl")
    private String imgUrl;

    @DatabaseField(columnName = "total")
    private int total;

    @DatabaseField(columnName = "remian")
    private int remain;

    @DatabaseField(columnName = "amount1")
    private int amount1;

    @DatabaseField(columnName = "amount2")
    private int amount2;

    public DataBaseBean() {

    }

    public int getAmount1() {
        return amount1;
    }

    public void setAmount1(int amount) {
        this.amount1 = amount;
    }

    public int getAmount2() {
        return amount2;
    }

    public void setAmount2(int amount) {
        this.amount2 = amount;
    }

    public int getGoodID() {
        return goodID;
    }

    public void setGoodID(int goodID) {
        this.goodID = goodID;
    }

    public DataBaseBean(int goodID, String title, String imgUrl, int total, int remain) {
        this.goodID = goodID;
        this.title = title;
        this.imgUrl = imgUrl;
        this.total = total;
        this.remain = remain;
        this.amount1 = 1;
        this.amount2 = 1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }
}
