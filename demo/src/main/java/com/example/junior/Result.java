package com.example.junior;
import java.util.ArrayList;

public class Result {
	
	private int numberOfWords;
	private ArrayList<String> words = new ArrayList<String>();
	
	public Result(String word) {
		
		this.numberOfWords = 1;
		this.words.add(word);
	}
	
	public int getNumberOfWords() {
		return numberOfWords;
	}
	
	public String getWords() {
		String formattedStr = "";
		for(String word : words) {
			formattedStr += word + " ";
		}
		return formattedStr;
	}
	
	public void populateWords(String word) {
		this.words.add(word);
		this.numberOfWords++;
	}
	
}
