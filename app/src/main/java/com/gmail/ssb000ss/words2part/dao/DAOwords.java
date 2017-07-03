package com.gmail.ssb000ss.words2part.dao;

import android.database.sqlite.SQLiteDatabase;

import com.gmail.ssb000ss.objects.WordList;
import com.gmail.ssb000ss.words2part.db.DBWords;
import com.gmail.ssb000ss.words2part.db.DBWordsHelper;

/**
 * Created by ssb000ss on 03.07.2017.
 */

public interface DAOwords {
    boolean addWord();

    boolean updateWord();

    boolean deleteWord();
}
