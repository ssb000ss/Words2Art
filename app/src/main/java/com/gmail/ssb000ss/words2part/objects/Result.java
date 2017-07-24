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

public class Result {
    private String from="";
    private String dest="";
    private String phrase="";
    private List<Tuc> tucs;


    public Result(JSONObject object)  {
        try {
            this.from = object.getString(WordConstants.KEY_FROM);
            this.dest = object.getString(WordConstants.KEY_DEST);
            this.phrase = object.getString(WordConstants.KEY_PHRASE);
            setTucs(object.getJSONArray(WordConstants.KEY_TUC));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public List<Tuc> getTucs() {
        return tucs;
    }

    public void setTucs(List<Tuc> tucs) {
        this.tucs = tucs;
    }

    public void setTucs(JSONArray array) {
        tucs=new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                JSONObject temp= (JSONObject) array.get(i);
                tucs.add(new Tuc(temp));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
