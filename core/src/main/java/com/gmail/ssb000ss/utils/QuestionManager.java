package com.gmail.ssb000ss.utils;

import com.gmail.ssb000ss.dao.WordList;
import com.gmail.ssb000ss.exceptions.WordException;
import com.gmail.ssb000ss.objects.Question;
import com.gmail.ssb000ss.objects.Word;

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
    public List<Question> getQuestions() throws WordException {
        int size = idList.size();
        for (Long s : idList) {
            int position = idList.indexOf(s);
            //проходим полностью по списку Ид, генерируем список вопросов
            questions.add(new Question(
                    wordList.getWordById(s),
                    getWordByPosition(getRandom(position,size)))
            );
        }
        return questions;
    }

    //метод для получения рандомных (неправельных ответов)слов из списка
    //получаем слово из списка слов(подав ид слов(получив ид по позиции из списка ид)) =)
    private List<Word> getWordByPosition(List<Integer> l) throws WordException {
        List<Word> items = new ArrayList<>();
        for (Integer i : l) {
            items.add(wordList.getWordById(idList.get(i)));
        }
        return items;
    }

    //метод для получения рандомных чисел из списка
    //positionOfId это позиция правильного ответа в списке
    private List<Integer> getRandom(int positionOfId, int size) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            while (true) {
                int c = rand(size);
                if ((c != positionOfId) && !(list.contains(c))) {
                    list.add(c);
                    break;
                }
            }
        }
        return list;
    }

    private int rand(int size) {
        return (int) (Math.random() * size);
    }
}



