package com.gmail.ssb000ss.words2part.db;

import android.provider.BaseColumns;

/**
 * Created by ssb000ss on 13.06.2017.
 */

public class DBWordsContract {
    //этот статичный метод нужен, чтобы был доступ к названиям таблицы, колон
    public static final class DBWordEntry implements BaseColumns {
        public static final String TABLE_NAME="dictionary";
        public static final String COLUMN_WORD="words";
        public static final String COLUMN_TRANSLATION="translation";
        public static final String COLOMN_STATISTIC="statistic";
        public static final int MEMORIZATION_LEVEL=50;
    }



}
