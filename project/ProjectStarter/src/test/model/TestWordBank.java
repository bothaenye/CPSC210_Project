package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestWordBank {
    private WordBank wordBank;
    
    @BeforeEach
    void runBefore() {
        wordBank = new WordBank();
    }

    @Test
    void testWordBank() {
        assertTrue(wordBank.getEasyWords().isEmpty());
        assertTrue(wordBank.getHardWords().isEmpty());
    }

    @Test
    void testGetHardWords() {
        assertTrue(wordBank.getHardWords().isEmpty());
    }

    @Test
    void testGetEasyWords() {
        assertTrue(wordBank.getEasyWords().isEmpty());
    }
    
    @Test
    void testGetSizeEasyWords() {
        assertEquals(0, wordBank.getSizeEasyWords());
    }

    @Test
    void testAddNewWord() {
        assertTrue(wordBank.getHardWords().isEmpty());
        wordBank.addNewWord("hello", "bonjour", "bon-jour");
        assertEquals("hello", wordBank.getHardWords().get(0).getWord());
        assertEquals("bonjour", wordBank.getHardWords().get(0).getTranslation());
        assertEquals("bon-jour", wordBank.getHardWords().get(0).getPronounciation());

        wordBank.addNewWord("tea", "thé", "t-ay");
        assertEquals("tea", wordBank.getHardWords().get(1).getWord());
        assertEquals("thé", wordBank.getHardWords().get(1).getTranslation());
        assertEquals("t-ay", wordBank.getHardWords().get(1).getPronounciation());
    }

    @Test
    void testMoveToEasyWords() {
        assertTrue(wordBank.getHardWords().isEmpty());
        wordBank.addNewWord("hello", "bonjour", "bon-jour");
        assertEquals(1, wordBank.getHardWords().size());
        wordBank.moveToEasyWords("hello");
        assertEquals(null, wordBank.getWordFromList("hello", wordBank.getHardWords()));
        assertEquals("hello", wordBank.getWordFromList("hello", wordBank.getEasyWords()).getWord());
        assertEquals(1, wordBank.getWordFromList("hello", wordBank.getEasyWords()).getDifficulty());
    }

    @Test
    void testRandomWord() {
        assertTrue(wordBank.getHardWords().isEmpty());
        wordBank.addNewWord("hello", "bonjour", "bon-jour");
        wordBank.addNewWord("tea", "thé", "t-ay");
        assertTrue(wordBank.listOfWords().contains(wordBank.randomWord().getWord()));
    }

    @Test
    void testDeleteWord() {
        assertEquals(true, wordBank.getHardWords().isEmpty());
        wordBank.addNewWord("hello", "bonjour", "bon-jour");
        wordBank.addNewWord("tea", "thé", "t-ay");
        wordBank.deleteWord("hello");
        assertEquals(false, 
                    wordBank.getHardWords().contains(wordBank.getWordFromList("hello", wordBank.getHardWords())));
        assertEquals(true, wordBank.getHardWords().contains(wordBank.getWordFromList("tea", wordBank.getHardWords())));
        wordBank.deleteWord("physics");
        assertEquals(false, 
                    wordBank.getHardWords().contains(wordBank.getWordFromList("physics", wordBank.getHardWords())));
    }

    @Test
    void testSearchWord() {
        assertTrue(wordBank.getHardWords().isEmpty());
        wordBank.addNewWord("hello", "bonjour", "bon-jour");
        wordBank.addNewWord("tea", "thé", "t-ay");
        assertEquals("Word: hello, Translation: bonjour, Pronounciation: bon-jour", wordBank.searchWord("hello"));
        assertEquals("That word is not logged.", wordBank.searchWord("CPSC 210"));
    }

    @Test
    void testListOfWords() {
        assertTrue(wordBank.getHardWords().isEmpty());
        wordBank.addNewWord("hello", "bonjour", "bon-jour");
        wordBank.addNewWord("tea", "thé", "t-ay");
        final ArrayList<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("tea");
        assertEquals(list, wordBank.listOfWords());
    }
}