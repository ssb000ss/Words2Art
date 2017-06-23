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

    //на вход конструктора подается лишь список словарей wordlist
    public QuestionManager(WordList wordList) {
        this.wordList = wordList;
        //создание объекта пока только нового
        questions = new ArrayList<>();
        //получение списка существующих id
        idList = wordList.getIdList();
    }

    //метод получение списка вопросников
    public List<Question> getQuestions() {
        int size = idList.size();
        for (Long s : idList) {
            int position = idList.indexOf(s);
            //проходим полностью по списку Ид, генерируем список вопросов, добавляем s correct answer, и лист
            questions.add(new Question(s, getIdByPosition(QuestionUtils.getRandom(position, size))));
        }
        return questions;
    }

    private List<Long> getIdByPosition(List<Integer> list) {
        List<Long> l=new ArrayList<>();
        l.add(idList.get(list.get(0)));
        l.add(idList.get(list.get(1)));
        l.add(idList.get(list.get(2)));
        return l;
    }
}



