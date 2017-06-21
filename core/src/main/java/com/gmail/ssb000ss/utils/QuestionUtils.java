package com.gmail.ssb000ss.utils;

import com.gmail.ssb000ss.dao.WordList;

import java.util.List;

/**
 * Created by ssb000ss on 21.06.2017.
 */


public class QuestionUtils {
    
//метод получения рандомных чисел
    public static int []getRandom(int position,int size){
        int mas[]=new int[3];
        for (int i = 0; i <3 ; i++) {
            while (true) {
                int c = rand(size);
                if (c != position) {
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
