package com.gmail.ssb000ss.objects;

/**
 * Created by ssb000ss on 21.06.2017.
 */
//todo у каждого слово должна быть статистика, после 50 поторений слово удалить.

public class Word {
    private long id;
    private String word;
    private String translation;

    public Word() {
    }

    public Word(long id, String word, String translation) {
        this.id = id;
        this.word = word;
        this.translation = translation;
    }

    public Word(String word) {
        this.word = word;
    }

    public Word(String word, String translation) {
        this.word = word;
        this.translation = translation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word1 = (Word) o;

        if (!word.equalsIgnoreCase(word1.word)) return false;
        return translation != null ? translation.equalsIgnoreCase(word1.translation) : word1.translation == null;
    }

    @Override
    public int hashCode() {
        int result = word.hashCode();
        result = 31 * result + (translation != null ? translation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", translation='" + translation + '\'' +
                '}';
    }
}
