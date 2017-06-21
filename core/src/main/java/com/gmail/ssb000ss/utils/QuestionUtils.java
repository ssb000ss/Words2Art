package com.gmail.ssb000ss.utils;

import com.gmail.ssb000ss.dao.WordList;

import java.util.List;

/**
 * Created by ssb000ss on 21.06.2017.
 */


public class QuestionUtils {

    private WordList wordList;
    private List<Long> idList;
    private List<Integer> positions;

    public QuestionUtils(WordList wordList) {
        this.wordList = wordList;
        idList=wordList.getIdList();
        setpositions();
    }

    private void setpositions() {
        for (int i = 0; i <idList.size(); i++) {
            positions.add(i);
        }
    }

    public static int []getrandom(int x){
        int mas[]=new int[3];
        for (int i = 0; i <3 ; i++) {
            int temp=Math.random();
        }
        return null;
    }

}
