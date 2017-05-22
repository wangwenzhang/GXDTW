package com.huitui.gxdt.yuliu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.huitui.gxdt.bean.JokeBean;
import com.huitui.gxdt.bean.PictureBean;
import com.huitui.gxdt.bean.VideoBean;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/1.
 */
public class DataBaseHelper1 extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = "yule.db";
    private static final int Version = 1;
    private static DataBaseHelper1 mInstance;
    private Context mContext;
    private Dao<PictureBean, Long> mUserDao;
    private Dao<JokeBean, Long> mjokeDao;
    private Dao<VideoBean, Long> mVideoDao;
    private Map<String,Dao> daos=new HashMap<String, Dao>();

    public DataBaseHelper1(Context context) {
        super(context, DB_NAME, null, Version);
        this.mContext = context;
    }
    /**
     * 帮助类单例化
     *
     * @param context
     * @return
     */
    public static DataBaseHelper1 getInstace(Context context) {
        if (mInstance == null) {
            synchronized (DataBaseHelper1.class) {
                if (mInstance == null) {
                    mInstance = new DataBaseHelper1(context);
                }
            }
        }
        return mInstance;
    }
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource , PictureBean.class);
            TableUtils.createTableIfNotExists(connectionSource , JokeBean.class);
            TableUtils.createTableIfNotExists(connectionSource , VideoBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
    //返回对应表Person的增删改查 dao类
    //两个泛型： 1. 表对应的Object对象
    // 2.id对应的类型
    public Dao<PictureBean, Long> getPictureDao() throws SQLException {
        if (mUserDao == null) {
            mUserDao = getDao(PictureBean.class);
        }
        return mUserDao;
    }
    public Dao<JokeBean, Long> getJokeDao() throws SQLException {
        if (mjokeDao == null) {
            mjokeDao = getDao(JokeBean.class);
        }
        return mjokeDao;
    }
    public Dao<VideoBean, Long> getVideoDao() throws SQLException {
        if (mVideoDao == null) {
            mVideoDao = getDao(VideoBean.class);
        }
        return mVideoDao;
    }
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
