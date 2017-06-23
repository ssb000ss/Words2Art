package com.gmail.ssb000ss.objects;

import com.gmail.ssb000ss.dao.WordList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssb000ss on 21.06.2017.
 */

public class Question {

    private long id;
    private long correctWordId;
    private List<Long> incorrectWords = new ArrayList<>(3);
    private Answer answer = null;


    public Question(long id, long correctWord, List<Long> incorrectWords) {
        this.id = id;
        this.correctWordId = correctWord;
        this.incorrectWords = incorrectWords;
    }

    public Question(long correctWord, List<Long> incorrectWords) {
        this.correctWordId = correctWord;
        this.incorrectWords = incorrectWords;
    }

    public long getCorrectWordId() {
        return correctWordId;
    }

    public void setCorrectWord(long correctWord) {
        this.correctWordId = correctWord;
    }

    public List<Long> getIncorrectWords() {
        return incorrectWords;
    }

    public void setIncorrectWords(List<Long> incorrectWords) {
        this.incorrectWords = incorrectWords;
    }

    public Answer getAnswer(long w) {
        if (answer != null) {
            return answer;
        } else {
            if (w == correctWordId) return answer = new Answer(correctWordId, true);
            else return answer = new Answer(correctWordId, true);
        }
    }
}
