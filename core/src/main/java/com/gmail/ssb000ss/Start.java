package com.gmail.ssb000ss;

import com.gmail.ssb000ss.objects.Word;

public class Start {
    public static void main(String[] args) {
        Word word=new Word("папа","father");
        Word word1=new Word("папа","father");
        if(word.equals(word1)) System.out.println("Da!!!");
        else System.out.println("NET!!!");
    }
}
