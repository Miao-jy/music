package com.example.lib_audio.mediaplayer.db;

import android.database.sqlite.SQLiteDatabase;

import com.example.lib_audio.app.AudioHelper;
import com.example.lib_audio.mediaplayer.model.AudioBean;
import com.example.lib_audio.mediaplayer.model.Favourite;

public class GreenDaoHelper {

    private final static String DB_NAME = "music_db";
    // 数据库帮助类，用来创建数据库，升级数据库
    private static DaoMaster.DevOpenHelper mHelper;
    // 最终创建好的数据库
    private static SQLiteDatabase mDb;
    // 管理数据库
    private static DaoMaster mDaoMaster;
    // 管理表，各种实体Dao
    private static DaoSession mDaoSession;

    public static void initDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(AudioHelper.getContext(), DB_NAME);
        mDb = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();
    }

    /**
     * 添加收藏
     */
    public static void addFavourite(AudioBean bean) {
        FavouriteDao favouriteDao = mDaoSession.getFavouriteDao();
        Favourite favourite = new Favourite();
        favourite.setAudioId(bean.getId());
        favourite.setAudioBean(bean);
        favouriteDao.insertOrReplace(favourite);
    }

    /**
     * 移除收藏
     */
    public static void removeFavourite(AudioBean bean) {
        FavouriteDao favouriteDao = mDaoSession.getFavouriteDao();
        Favourite favourite = selectFavourite(bean);
        favouriteDao.delete(favourite);
    }

    /**
     * 查找收藏
     */
    public static Favourite selectFavourite(AudioBean bean) {
        FavouriteDao favouriteDao = mDaoSession.getFavouriteDao();
        return favouriteDao.queryBuilder().where(FavouriteDao.Properties.AudioId.eq(bean.id)).unique();
    }
}
