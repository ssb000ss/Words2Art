package com.gmail.ssb000ss.words2part.translate;

public class Phrase {

	String text;
	private String language;
	
	public Phrase(String text, String language){
		this.text = text;
		this.language = language;
	}
	
	@Override
	public String toString(){
		return this.text;
	}
	
}
