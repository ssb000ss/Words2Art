package com.gmail.ssb000ss.utils;

import com.gmail.ssb000ss.dao.WordList;
import com.gmail.ssb000ss.objects.Question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

/**
 * Created by ssb000ss on 21.06.2017.
 */

public class QuestionManager {
    private WordList wordList;
    private List<Question> questions;
    private List<Long> idList;

    public QuestionManager(WordList wordList) {
        this.wordList = wordList;
        questions = new ArrayList<>();
        idList = wordList.getIdList();
    }

    public List<Question> getQuestions() {
        int size = idList.size();
        for (Long s : idList) {
            int position = idList.indexOf(s);
            int mas[] = QuestionUtils.getRandom(position, size);
            questions.add(new Question(s, getIdByPosition(mas)));
        }
        return questions;
    }
    //todo остановился на том что генерирую вопросы надо получить список ид, функция генерация любых ответов, получить список ответов
    private long[] getIdByPosition(int[] mas) {
        return new long[] {idList.get(mas[0]),idList.get(mas[1]),idList.get(mas[2])};
        }
    }



