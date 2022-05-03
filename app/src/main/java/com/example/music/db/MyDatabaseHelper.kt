package com.example.music.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context, name: String, version: Int):
    SQLiteOpenHelper(context, name, null, version) {

    private val createPhoto = "create table Photo (" +
            "id integer primary key autoincrement," +
            "name text," +
            "time text," +
            "width real," +
            "height real," +
            "address text)"

    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL(createPhoto)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        p0.execSQL("drop table if exists Photo")
        onCreate(p0)
    }

}