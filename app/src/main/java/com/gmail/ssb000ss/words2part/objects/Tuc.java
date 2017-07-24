package com.gmail.ssb000ss.words2part.objects;

import com.gmail.ssb000ss.words2part.WordConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssb000ss on 24.07.2017.
 */

public class Tuc {

    private Phrase phrase;
    private List<Meaning> meanings;

    public Tuc(JSONObject object) throws JSONException {
        this.phrase = new Phrase(object.getJSONObject(WordConstants.KEY_PHRASE));
        setMeanings(object.getJSONArray(WordConstants.KEY_MEANINGS));
    }

    public void setMeanings(JSONArray array) {
        this.meanings=new ArrayList<>();
        if(array.length()!=0) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject temp=(JSONObject) array.get(i);
                    this.meanings.add(new Meaning(temp));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        else this.meanings=new ArrayList<>();
    }

    public Phrase getPhrase() {
        return phrase;
    }

    public void setPhrase(Phrase phrase) {
        this.phrase = phrase;
    }

    public List<Meaning> getMeanings() {
        return meanings;
    }

    public void setMeanings(List<Meaning> meanings) {
        this.meanings = meanings;
    }
}
