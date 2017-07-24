package com.gmail.ssb000ss.words2part.objects;

import org.json.JSONException;
import org.json.JSONObject;

import static com.gmail.ssb000ss.words2part.WordConstants.KEY_LANGUAGE;
import static com.gmail.ssb000ss.words2part.WordConstants.KEY_TEXT;

/**
 * Created by ssb000ss on 24.07.2017.
 */

public class Phrase {

    private String language="";
    private String text="";

    public Phrase(JSONObject object)  {
        try {
            this.language = object.getString(KEY_LANGUAGE);
            this.text = object.getString(KEY_TEXT);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
