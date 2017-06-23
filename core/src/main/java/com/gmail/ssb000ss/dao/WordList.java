package com.gmail.ssb000ss.dao;

import com.gmail.ssb000ss.exceptions.WordException;
import com.gmail.ssb000ss.objects.Word;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssb000ss on 21.06.2017.
 */

public class WordList {
    //список всех импортированных слов
    private List<Word> wordList = new ArrayList<>();
    private List<Long> idList=new ArrayList<>();

    public WordList(List<Word> wordList) {
        this.wordList = wordList;
        setIdList();
    }

    private void setIdList() {
        for (Word w : wordList) {
            idList.add(w.getId());
        }
    }

    //получить слово по ид
    public Word getWordById(long id) throws WordException {
        if (hasWordById(id)) {
            for (Word w : wordList) {
                if (w.getId() == id) {
                    return w;
                }
            }
        }
        throw new WordException("This word is not in the list");
    }

    //получить Объект по слову
    public Word getWordByStringWord(String word) throws WordException {
        for (Word w : wordList) {
            if (w.getWord().equals(word)) {
                return w;
            }
        }
        throw new WordException("This word is not in the list");
    }

    //получить слово по переводу
    public Word getWordByTranslation(String translation) throws WordException {
        for (Word w : wordList) {
            if (w.getWord().equals(translation)) {
                return w;
            }
        }
        throw new WordException("This word is not in the list");
    }

    //получиьт лист всех импортированных слов
    public List<Word> getAll() {
        return wordList;
    }

    //получить лист существующих ид
    public List<Long> getIdList() {
        return idList;
    }

    public boolean addWord(Word word) {
        return wordList.add(word) || idList.add(word.getId());

    }

    public boolean deleteWord(Word word) {
        return wordList.remove(word) || idList.remove(word.getId());
    }

    public boolean updateWord(long id, String newWord, String newTranslation) throws WordException {
        if (hasWordById(id)) {
            wordList.remove(getWordById(id));
            wordList.add(new Word(id, newWord, newTranslation));
            return true;
        } else throw new WordException("This word is not in the list");
    }

    public boolean hasWordById(long id) {
        return idList.contains(id);
    }

}
