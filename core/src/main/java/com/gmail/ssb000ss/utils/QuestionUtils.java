package com.gmail.ssb000ss.utils;

import com.gmail.ssb000ss.dao.WordList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssb000ss on 21.06.2017.
 */


public class QuestionUtils {
    
//метод получения рандомных чисел
    public static List<Integer>getRandom(int position,int size){
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            while (true) {
                int c = rand(size);
                if ((c != position)&&!(list.contains(c))) {
                    list.add(c);
                    break;
                }
            }
        }
        return list;
    }

    private static int rand(int size) {
        return (int) (Math.random()*size);
    }

}
