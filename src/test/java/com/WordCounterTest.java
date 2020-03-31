package com;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WordCounterTest {
	@InjectMocks
	WordCounter wordCounter;
	
	@Mock
	Translator translator;
	
	@Before
	public void setup () {
		when(translator.translate("flower")).thenReturn("flower");
		when(translator.translate("flor")).thenReturn("flower");
		when(translator.translate("blume")).thenReturn("flower");		
	} 

	@Test
	public void shouldAllowToAddWords() {
		assertTrue(wordCounter.addWord("flower"));
	}
	
	@Test
	public void shouldNotAllowToAddWordsWithNonAlphabeticCharacters() {
		assertFalse(wordCounter.addWord("flowe4r"));
	}
	
	@Test
	public void shouldReturnTheAddedCount() {
		String word = "flower";
		wordCounter.addWord(word);
		assertEquals(1, wordCounter.getAddedCount(word));
	}

	@Test
	public void shouldTreatSameWordsWrittenInDifferentLanguagesAsSameWord() {
		when(translator.translate("flower")).thenReturn("flower");
		when(translator.translate("flor")).thenReturn("flower");
		when(translator.translate("blume")).thenReturn("flower");
		
		wordCounter.addWord("flower");
		wordCounter.addWord("flor");
		wordCounter.addWord("blume");
		
		assertEquals(3, wordCounter.getAddedCount("flower"));
	}
}
















