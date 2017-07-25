package com.gmail.ssb000ss.words2part.translate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TranslationGroup {

	private String originalphrase;
	private String originalLanguage;
	private String destinationLanguage;
	private JSONObject jsonObject;
	private ArrayList<Translation> translations = new ArrayList<Translation>();
	
	/**
	 * A translationGroup object represents the collections of data returned from the translation API.
	 * It holds a list of translations which each have a phrase and a meaning 
	 * @param jsonObject
	 */
	public TranslationGroup(JSONObject jsonObject){
		this.jsonObject =jsonObject;
		try {
			this.originalphrase = (String) jsonObject.get("phrase");
			this.originalLanguage = (String) jsonObject.get("from" );
			this.destinationLanguage =(String) jsonObject.get("dest");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		this.createJSONobjects();
	}
	
	public TranslationGroup(){
		
	}
	
	public String getPhrase(){
		return this.originalphrase;
	}
	
	public String getDestinationLanguage(){
		return this.destinationLanguage;
	}
	
	public void addPhrase(String phrase){
		this.originalphrase = phrase;
	}
	
	public void addTranslation(ArrayList<Translation> translation){
		this.translations = translation;
	}
	
	public String getOriginalLanguage(){
		return this.originalLanguage;
	}
	
	
	

	public String getMeaning(int index){
		String text = translations.get(index).getMeanings().get(index).text;
		return text;
	}
	
	
	private void createJSONobjects(){
		int containsCount = 0;
		if(this.jsonObject==null){
			return ;
		}
		try {
			JSONArray tuc = (JSONArray) this.jsonObject.get("tuc");
			for (int i=0;i<tuc.length();i++) {

				JSONObject transJSON = (JSONObject) tuc.get(i);

				if (transJSON.has("phrase") && transJSON.has("meanings")) {
					Translation transt = new Translation();

					// get meanings
					JSONArray meanings = (JSONArray) transJSON.get("meanings");
					for (int j=0;j<meanings.length();j++) {
						JSONObject mm = (JSONObject) meanings.get(j);

						String meaningText = (String) mm.get("text");
						String meaningLang = (String) mm.get("language");

						transt.meanings.add(new Meaning(meaningText, meaningLang));
					}

					// get phrases
					JSONObject phraseJSON = (JSONObject) transJSON.get("phrase");
					String text = (String) phraseJSON.get("text");
					String lang = (String) phraseJSON.get("language");
					Phrase phrase = new Phrase(text, lang);
					transt.addPhrase(phrase);
					this.translations.add(transt);

					containsCount++;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Translation> getTranslations(){
		return this.translations;
	}
	
	public String getFirstAvailablePhrase(){
		for(Translation t : translations){
			if(t.getPhrase()!=null){
				return t.getPhrase().toString();
			}
		}
		return "No Translation Available";
	}
	
	public String getFirstAvailableMeaning(){
		for(Translation t: translations){
			for(Meaning m : t.meanings){
				return m.text;
			}
		}
		return "No Meaning Available";
	}
	
}
