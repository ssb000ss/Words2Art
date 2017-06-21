package com.gmail.ssb000ss.exceptions;

/**
 * Created by ssb000ss on 21.06.2017.
 */

public class WordException extends Exception {
    public WordException() {
        super();
    }

    public WordException(String s) {
        super(s);
    }

    public WordException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WordException(Throwable throwable) {
        super(throwable);
    }
}
