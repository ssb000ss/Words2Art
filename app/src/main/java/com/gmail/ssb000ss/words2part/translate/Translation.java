package com.gmail.ssb000ss.words2part.translate;

import java.util.ArrayList;

public class Translation {

	ArrayList<Meaning> meanings = new ArrayList<Meaning>();
	private Phrase phrase;
	
	public Translation(){
	}
	
	public void addPhrase(Phrase p){
		this.phrase = p;
	}
	
	public void addMeaning(Meaning meaning){
		this.meanings.add(meaning);
	}
	
	public Phrase getPhrase(){
		return this.phrase;
	}
	
	public ArrayList<Meaning> getMeanings(){
		return this.meanings;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		
		if(phrase!=null){
			sb.append("phrase : ");
			sb.append(phrase.text);
			sb.append("  ");
		
		}
		if(meanings.size() > 0){
			sb.append("meanings : ");
			for(Meaning m : meanings){
				sb.append(m.text);
				sb.append(" , ");
			}	
		}

		sb.append("\n");
		return sb.toString();		
	}
	
}
