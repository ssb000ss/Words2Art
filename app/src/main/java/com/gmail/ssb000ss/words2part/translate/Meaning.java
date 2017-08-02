package com.gmail.ssb000ss.words2part.translate;

public class Meaning {

	String text;
	private String lang;
	
	public Meaning(String text, String language){
		this.text = text;
		this.lang = language;
	}

    public String getText() {
        return text;
    }
}
