package com.gmail.ssb000ss.words2part.db;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gENERATION on 13.06.2017.
 */

public class TestUtils {
    //TODO удалить после того как протестирую функционал БД
    public static void insertTestWord(SQLiteDatabase sqLiteDatabase) {
        if (sqLiteDatabase == null) {
            return;
        } else {
            List<ContentValues> list = new ArrayList<>();
            ContentValues cv = new ContentValues();

            cv.put(DBWordsContract.DBWordEntry.COLUMN_WORD, "first");
            cv.put(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION, "первый");
            list.add(cv);

            cv = new ContentValues();
            cv.put(DBWordsContract.DBWordEntry.COLUMN_WORD, "second");
            cv.put(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION, "Второй");
            list.add(cv);

            cv = new ContentValues();
            cv.put(DBWordsContract.DBWordEntry.COLUMN_WORD, "third");
            cv.put(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION, "третий");
            list.add(cv);

            cv = new ContentValues();
            cv.put(DBWordsContract.DBWordEntry.COLUMN_WORD, "fourth");
            cv.put(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION, "Четвертый");
            list.add(cv);

            cv = new ContentValues();
            cv.put(DBWordsContract.DBWordEntry.COLUMN_WORD, "fifth");
            cv.put(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION, "пятый");
            list.add(cv);

            try {

                sqLiteDatabase.beginTransaction();
                sqLiteDatabase.delete(DBWordsContract.DBWordEntry.TABLE_NAME, null, null);
                for (ContentValues c : list) {

                    sqLiteDatabase.insert(DBWordsContract.DBWordEntry.TABLE_NAME, null, c);
                }
                sqLiteDatabase.setTransactionSuccessful();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                sqLiteDatabase.endTransaction();
            }
        }
    }

}
