package com.huitui.gxdt.yuliu;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.huitui.gxdt.bean.JokeBean;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wangwenzhang on 2016/11/16.
 */
public class JokeDao {
    private Context mcontext;
    private  Dao<JokeBean, Long> jokeDao;
    private DataBaseHelper1 helper1;
    public JokeDao(Context context)  {//初始化
        this.mcontext=context;
        try {
            helper1=DataBaseHelper1.getInstace(mcontext);
            helper1.getWritableDatabase();
            jokeDao=helper1.getJokeDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void add(JokeBean jokeBean) {//添加数据
        int ID=jokeBean.getImageID();
        try {
            if (!isAddToCart(ID)){
                jokeDao.create(jokeBean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public  void removeData(JokeBean jokeBean){//根据id  删除数据
        try {

            jokeDao.delete(jokeBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  List<JokeBean> getDatabse(){//获取数据
        try {
            List<JokeBean> dataBaseBeen = jokeDao.queryForAll();
            return dataBaseBeen;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public  boolean isAddToCart(int id) {
        QueryBuilder<JokeBean, Long> builder = jokeDao.queryBuilder();
        try {
            builder.where().like("imageID", id);
            List<JokeBean> data = builder.query();
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
