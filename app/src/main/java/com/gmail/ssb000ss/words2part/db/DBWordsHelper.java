package com.gmail.ssb000ss.words2part.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ssb000ss on 13.06.2017.
 */

@SuppressWarnings("ALL")
public class DBWordsHelper extends SQLiteOpenHelper {
    //назвние бд
    private static final String DATABASE_NAME = "words.db";
    //версия бд
    private static final int DATABASE_VERSION = 1;

    public DBWordsHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //метод для создания бд в памяти смартфона, нужно создать только один раз в начале установки
    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_WAITLIST_TABLE = "CREATE TABLE " + DBWordsContract.DBWordEntry.TABLE_NAME + " (" +
                DBWordsContract.DBWordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBWordsContract.DBWordEntry.COLUMN_WORD + " TEXT NOT NULL, " +
                DBWordsContract.DBWordEntry.COLUMN_TRANSLATION + "," +
                DBWordsContract.DBWordEntry.COLUMN_STATISTIC +" INTEGER NOT NULL); ";
        db.execSQL(SQL_CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBWordsContract.DBWordEntry.TABLE_NAME);
        onCreate(db);
    }
}
