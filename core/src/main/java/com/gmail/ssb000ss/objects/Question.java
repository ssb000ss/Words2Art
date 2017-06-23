package com.gmail.ssb000ss.objects;

import com.gmail.ssb000ss.dao.WordList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ssb000ss on 21.06.2017.
 */

public class Question {

    //объект вопрос не должен состоять из списка просто ид, а он должен состоять
    // из спиcка объектов слов и правильного ответа на слово
    private long idQuestion;


    //как раз для универсальности не ограничиваем длину Set(можно добалять сколько хочешь вариантов)
    private Set<Word> items = new HashSet<>();
    private Answer answer = null;

    public Question(Word correctWord, List<Word> listWord) {
        this.correctWord = correctWord;
        setItems(listWord);
    }

    //на вход подаём ид правельного ответа, и для упрощения список ответов
    public Question(long id, Word correctWord, List<Word> listWord) {
        this.idQuestion = id;
        this.correctWord = correctWord;
        setItems(listWord);
    }

    public long getCorrectWordId() {
        return correctWord.getId();
    }

    public void setCorrectWord(Word correctWord) {
        this.correctWord = correctWord;
    }

    public Answer getAnswer(long w) {
        if (answer != null) {
            return answer;
        } else {
            if (w == correctWord.getId()) return answer = new Answer(correctWord.getId(), true);
            else return answer = new Answer(correctWord.getId(), false);
        }
    }

    public Answer getAnswer(Word w){
        return getAnswer(w.getId());
    }

    public void setItems(List<Word> list) {
        items.addAll(list);
        items.add(correctWord);
    }

    public long getIdQuestion() {
        return idQuestion;
    }
    public Set<Word> getItems() {
        return items;
    }

    public Word getCorrectWord() {

        return correctWord;
    }

    private Word correctWord;
}
