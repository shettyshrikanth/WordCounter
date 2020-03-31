package com;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.LongAdder;

public class WordCounter {
	private String CHARSONLY_PATTERN = "[a-zA-Z]+";
	
	private Translator translator;
	
	private ConcurrentMap<String, LongAdder> wordCountMap = new ConcurrentHashMap<>();

	public boolean addWord(String word) {
		Objects.requireNonNull(word);
		
		if(word.matches(CHARSONLY_PATTERN)) {
			String englishWord = translator.translate(word);
			
			wordCountMap.computeIfAbsent(englishWord, k -> new LongAdder()).increment();
			
			return true;
		} else {		
			return false;
		}
	}

	public long getAddedCount(String word) {
		return wordCountMap.get(word).sum();
	}
	
	public void setTranslator(Translator translator) {
		this.translator = translator;
	}
}
