package com.gmail.ssb000ss.objects;

/**
 * Created by ssb000ss on 21.06.2017.
 */

public class Answer {
    private long id;
    private boolean isCorrect;

    public Answer(long id, boolean isCorrect) {
        this.id = id;
        this.isCorrect = isCorrect;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setStatus(boolean status) {
        this.isCorrect = status;
    }
}
