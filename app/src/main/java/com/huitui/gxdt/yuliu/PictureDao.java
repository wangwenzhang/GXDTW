package com.huitui.gxdt.yuliu;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.huitui.gxdt.bean.PictureBean;

import java.sql.SQLException;
import java.util.List;



/**
 * Created by wangwenzhang on 2016/11/16.
 */
public class PictureDao {
    private Context mcontext;
    private  Dao<PictureBean, Long> pictureDao;
    private DataBaseHelper1 helper1;
    public PictureDao(Context context)  {//初始化
        this.mcontext=context;
        try {
            helper1=DataBaseHelper1.getInstace(mcontext);
            helper1.getWritableDatabase();
            pictureDao=helper1.getPictureDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void add(PictureBean pictureBean) {//添加数据
        int ID=pictureBean.getID();
        try {
            if (!isAddToCart(ID)){
                pictureDao.create(pictureBean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public  void removeData(PictureBean pictureBean){//根据id  删除数据
        try {

            pictureDao.delete(pictureBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  List<PictureBean> getDatabse(){//获取数据
        try {
            List<PictureBean> dataBaseBeen = pictureDao.queryForAll();
            return dataBaseBeen;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public  boolean isAddToCart(int id) {
        QueryBuilder<PictureBean, Long> builder = pictureDao.queryBuilder();
        try {
            builder.where().like("ID", id);
            List<PictureBean> data = builder.query();
            if (data.size() == 0) {
                return false;
            } else {

                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
