package com.gmail.ssb000ss.words2part.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gmail.ssb000ss.objects.Word;

import java.util.ArrayList;

import java.util.List;

/**
 * Created by ssb000ss on 13.06.2017.
 */

//класс создан для работы с бд в андроиде
public class DBWords {
    //бд
    private SQLiteDatabase mDb;
    //объект курсор который хранить в себе все данные с бд
    Cursor cursor;

    public DBWords(SQLiteDatabase mDb) {
        this.mDb = mDb;
        this.cursor = getAll();
    }

    //добавление слова в бд
    public long addWord(String word, String translation) {
        ContentValues cv = new ContentValues();
        cv.put(DBWordsContract.DBWordEntry.COLUMN_WORD, word);
        cv.put(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION, translation);
        return mDb.insert(DBWordsContract.DBWordEntry.TABLE_NAME, null, cv);
    }

    public boolean updateWord(long id, String newWord, String newTranslation) {
        if (!(getWordById(id) == null)) {
            ContentValues cv = new ContentValues();
            cv.put(DBWordsContract.DBWordEntry.COLUMN_WORD, newWord);
            cv.put(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION, newTranslation);
            return mDb.update(DBWordsContract.DBWordEntry.TABLE_NAME, cv, DBWordsContract.DBWordEntry._ID + "=" + id, null) > 0;
        } else return false;
    }

    public Word getWordById(long id) {
        cursor = mDb.rawQuery("SELECT * FROM " + DBWordsContract.DBWordEntry.TABLE_NAME +
                " WHERE " + DBWordsContract.DBWordEntry._ID + "=" + id, null);
        cursor.moveToFirst();
        String word = cursor.getString(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLUMN_WORD));
        String translation = cursor.getString(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION));
        int statistic = cursor.getInt(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLOMN_STATISTIC));
        return new Word(id, word, translation, statistic);
    }

    private void swapCursor() {
        cursor.close();
        this.cursor = getAll();
    }

    public boolean deleteWord(Word word) {
        return mDb.delete(DBWordsContract.DBWordEntry.TABLE_NAME, DBWordsContract.DBWordEntry._ID + "=" + word.getId(), null) > 0;
    }

    public boolean deleteWord(long id) {
        return mDb.delete(DBWordsContract.DBWordEntry.TABLE_NAME, DBWordsContract.DBWordEntry._ID + "=" + id, null) > 0;
    }

    public Cursor getAll() {
        return mDb.query(DBWordsContract.DBWordEntry.TABLE_NAME
                , null
                , null
                , null
                , null
                , null
                , DBWordsContract.DBWordEntry._ID
        );
    }

    //метод для получения всех значений с курсора
    public List<Word> getList() {
        List<Word> list = new ArrayList<>();
        cursor.moveToPosition(0);
        for (int i = 0; i < cursor.getCount(); i++) {
            getWordFromCursor(list);
        }
        return list;
    }

    //метод который используют для получения слов шагая по всей таблице,
    private void getWordFromCursor(List<Word> list) {
        //получаем слово с курсова
        long id = cursor.getLong(cursor.getColumnIndex(DBWordsContract.DBWordEntry._ID));
        String word = cursor.getString(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLUMN_WORD));
        String translate = cursor.getString(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLUMN_TRANSLATION));
        int statistic = cursor.getInt(cursor.getColumnIndex(DBWordsContract.DBWordEntry.COLOMN_STATISTIC));
        //добовляем его в лист
        list.add(new Word(id, word, translate, statistic));
        //меняем расположение курсора на следующее значение
        cursor.moveToNext();
    }

    private boolean upCount(long id) {
        if (!(getWordById(id) == null)) {
            ContentValues cv= new ContentValues();
            int old = getWordById(id).getStatistic();
            if (old >= DBWordsContract.DBWordEntry.MEMORIZATION_LEVEL) {
                deleteWord(id);
            } else {
                cv.put(DBWordsContract.DBWordEntry.COLOMN_STATISTIC, ++old);
            }
            return mDb.update(DBWordsContract.DBWordEntry.TABLE_NAME, cv, DBWordsContract.DBWordEntry._ID + "=" + id, null) > 0;
        } else return false;
        }
}
