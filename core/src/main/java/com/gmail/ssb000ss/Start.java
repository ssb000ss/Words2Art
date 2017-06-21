package com.gmail.ssb000ss;

import com.gmail.ssb000ss.objects.Word;
import com.gmail.ssb000ss.utils.QuestionUtils;

public class Start {
    public static void main(String[] args) {
        int x=5;
        int size=15;
        int mas[]=QuestionUtils.getRandom(x,size);
        System.out.println(x);
        for (int i = 0; i <3 ; i++) {
            System.out.println(mas[i]);
        }
    }
}
