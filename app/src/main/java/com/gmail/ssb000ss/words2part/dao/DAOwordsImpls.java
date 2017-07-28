package com.gmail.ssb000ss.words2part.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.gmail.ssb000ss.exceptions.WordException;
import com.gmail.ssb000ss.objects.Word;
import com.gmail.ssb000ss.objects.WordList;
import com.gmail.ssb000ss.words2part.Constants;
import com.gmail.ssb000ss.words2part.db.DBWords;
import com.gmail.ssb000ss.words2part.db.DBWordsHelper;

/**
 * Created by ssb000ss on 27.06.2017.
 */

public class DAOwordsImpls {
    Context context;
    private DBWords dbWords;
    private WordList list;
    private DBWordsHelper helper;
    private SQLiteDatabase database;

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public DAOwordsImpls(Context context) {
        this.context=context;
        init(context);
    }

    private void init(Context context) {
        helper=new DBWordsHelper(context);
        database=helper.getWritableDatabase();
        dbWords=new DBWords(database);
        list= new WordList(dbWords.getList());
    }


    public boolean addWord(String word,String translation) {
        long id=dbWords.addWord(word,translation);
        if(id>0){
            Toast toast=Toast.makeText(context,"Добавлено",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP,0,200);
            toast.show();
            return list.addWord(new Word(id,word,translation));
        }else return false;
    }


    public boolean updateWord(long id, String newWord, String newTranslation) {
        if (dbWords.updateWord(id,newWord,newTranslation)){
            try {
                list.updateWord(id,newWord,newTranslation);
            } catch (WordException e) {
                e.printStackTrace();
            }
            return true;
        }else return false;
    }


    public boolean deleteWord(long id) {
        if(dbWords.deleteWord(id)){
            try {
                list.deleteWord(id);
            } catch (WordException e) {
                e.printStackTrace();
            }
            return true;
        }else return false;
    }

    public WordList getList() {
        return list;
    }

    public boolean upCount(long id) throws WordException {
        if(dbWords.upCount(id)){
            list.upCount(id);
            if(dbWords.getWordById(id).getStatistic()== Constants.MEMORIZATION_LEVEL){
                Log.d("upcount", "удалили "+dbWords.getWordById(id).getWord());
                deleteWord(id);

            }
            return true;
        }else return false;
    }
}
