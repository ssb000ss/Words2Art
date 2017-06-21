package com.gmail.ssb000ss.utils;

import com.gmail.ssb000ss.dao.WordList;

import java.util.List;

/**
 * Created by ssb000ss on 21.06.2017.
 */


public class QuestionUtils {


    public QuestionUtils(WordList wordList) {
        this.wordList = wordList;
        idList=wordList.getIdList();
        setpositions();
        size=this.positions.size();
    }


    public static int []getrandom(int x,int size){
        int mas[]=new int[3];
        for (int i = 0; i <3 ; i++) {
            while (true) {
                int c = rand(size);
                if (c != x) {
                    mas[i] = c;
                    break;
                }
            }

        }
        return mas;
    }

    private static int rand(int size) {
        return (int) (Math.random()*size);
    }

}
