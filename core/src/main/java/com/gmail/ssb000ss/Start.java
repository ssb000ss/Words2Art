package com.gmail.ssb000ss;

import com.gmail.ssb000ss.dao.WordList;
import com.gmail.ssb000ss.objects.Question;
import com.gmail.ssb000ss.objects.Word;
import com.gmail.ssb000ss.utils.QuestionManager;
import com.gmail.ssb000ss.utils.QuestionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        WordList wordList=new WordList(TestWordList());
        QuestionManager questionManager=new QuestionManager(wordList);
        List<Question>questions=questionManager.getQuestions();
        for (Question question:questions) {
            System.out.println(question.getCorrectWordId()+" "+question.getIncorrectWords());
        }

//        int x=5;
//        int size=20;
//        for (int i = 0; i <10 ; i++) {
//            List<Integer> list=QuestionUtils.getRandom(x,size);
//            list.add(x);
//            HashSet<Integer>set=new HashSet<>();
//            set.addAll(list);
//            System.out.println(set);

        }


    private static List<Word> TestWordList() {
        List<Word> words=new ArrayList<>();
        words.add(new Word(1,"mama","Mother"));
        words.add(new Word(2,"papa","Father"));
        words.add(new Word(3,"sestra","Sister"));
        words.add(new Word(4,"brat","Brother"));
        words.add(new Word(5,"mashina","Car"));
        words.add(new Word(6,"solnce","Sun"));
        words.add(new Word(7,"voda","Water"));
        words.add(new Word(8,"stol","Table"));
        words.add(new Word(9,"mywka","Mouse"));
        words.add(new Word(10,"lisa","Fox"));
        words.add(new Word(11,"kowka","Cat"));
        words.add(new Word(12,"sobaka","Dog"));
        return words;

    }
}

