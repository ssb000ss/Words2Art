package com.gmail.ssb000ss.utils;

import com.gmail.ssb000ss.dao.WordList;
import com.gmail.ssb000ss.objects.Question;

import java.util.HashSet;
import java.util.List;

/**
 * Created by ssb000ss on 21.06.2017.
 */

public class QuestionManager {
    private WordList wordList;
    private HashSet<Question> questions;

    public QuestionManager(WordList wordList) {
        this.wordList = wordList;
        setQuestions();
    }

    private void setQuestions() {
        questions
    }

    //todo остановился на том что генерирую вопросы надо получить список ид, функция генерация любых ответов, получить список ответов

}
