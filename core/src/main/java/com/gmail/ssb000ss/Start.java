package com.gmail.ssb000ss;

import com.gmail.ssb000ss.objects.Word;
import com.gmail.ssb000ss.utils.QuestionUtils;

import java.util.List;

public class Start {
    public static void main(String[] args) {
        int x=5;
        int size=15;
        List<Integer> list=QuestionUtils.getRandom(x,size);
        System.out.println(x+" "+list);

    }
}
