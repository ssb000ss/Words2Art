package com.gmail.ssb000ss.words2part.translate;

import com.gmail.ssb000ss.words2part.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class TranslationGroup {

    private String originalphrase;
    private String originalLanguage;
    private String destinationLanguage;
    private JSONObject jsonObject;
    private ArrayList<Translation> translations = new ArrayList<>();


    public TranslationGroup(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        try {
            this.originalphrase = (String) jsonObject.get(Constants.KEY_PHRASE);
            this.originalLanguage = (String) jsonObject.get(Constants.KEY_FROM);
            this.destinationLanguage = (String) jsonObject.get(Constants.KEY_DEST);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.createJSONobjects();
    }

    public TranslationGroup() {

    }

    public String getPhrase() {
        return this.originalphrase;
    }

    public String getDestinationLanguage() {
        return this.destinationLanguage;
    }

    public void addPhrase(String phrase) {
        this.originalphrase = phrase;
    }

    public void addTranslation(ArrayList<Translation> translation) {
        this.translations = translation;
    }

    public String getOriginalLanguage() {
        return this.originalLanguage;
    }


    public String getMeaning(int index) {
        return translations.get(index).getMeanings().get(index).text;
    }


    private void createJSONobjects() {
        if (this.jsonObject == null) {
            return;
        }
        try {
            JSONArray tuc = (JSONArray) this.jsonObject.get(Constants.KEY_TUC);
            for (int i = 0; i < tuc.length(); i++) {

                JSONObject transJSON = (JSONObject) tuc.get(i);

                if (transJSON.has(Constants.KEY_PHRASE) && transJSON.has(Constants.KEY_MEANINGS)) {
                    Translation transt = new Translation();

                    // get meanings
                    JSONArray meanings = (JSONArray) transJSON.get(Constants.KEY_MEANINGS);
                    for (int j = 0; j < meanings.length(); j++) {
                        JSONObject mm = (JSONObject) meanings.get(j);

                        String meaningText = (String) mm.get(Constants.KEY_TEXT);
                        String meaningLang = (String) mm.get(Constants.KEY_LANGUAGE);

                        transt.meanings.add(new Meaning(meaningText, meaningLang));
                    }

                    // get phrases
                    JSONObject phraseJSON = (JSONObject) transJSON.get(Constants.KEY_PHRASE);
                    String text = (String) phraseJSON.get(Constants.KEY_TEXT);
                    String lang = (String) phraseJSON.get(Constants.KEY_LANGUAGE);
                    Phrase phrase = new Phrase(text, lang);
                    transt.addPhrase(phrase);
                    this.translations.add(transt);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Translation> getTranslations() {
        return this.translations;
    }

    public String getFirstAvailablePhrase() {
        for (Translation t : translations) {
            if (t.getPhrase() != null) {
                return t.getPhrase().toString();
            }
        }
        return "No Translation Available";
    }

    public String getFirstAvailableMeaning() {
        for (Translation t : translations) {
            for (Meaning m : t.meanings) {
                return m.text;
            }
        }
        return "No Meaning Available";
    }

}
