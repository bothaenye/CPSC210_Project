package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestWord {
    private Word word;
    
    @BeforeEach
    void runBefore() {
        word = new Word("hello", "bonjour", "bon-jour");
    }

    @Test
    void testWord() {
        assertEquals("hello", word.getWord());
        assertEquals("bonjour", word.getTranslation());
        assertEquals("bon-jour", word.getPronounciation());
        assertEquals(0, word.getDifficulty());
    }

    @Test
    void testGetWord() {
        assertEquals("hello", word.getWord());
    }

    @Test
    void testGetTranslation() {
        assertEquals("bonjour", word.getTranslation());
    }

    @Test
    void testGetPronounciation() {
        assertEquals("bon-jour", word.getPronounciation());
    }

    @Test
    void testGetDifficulty() {
        assertEquals(0, word.getDifficulty());
    }

    @Test
    void testMarkEasy() {
        word.mark("Hard");
        assertEquals(0, word.getDifficulty());
        word.mark("Easy");
        assertEquals(1, word.getDifficulty());
    }

    @Test
    void testMarkHard() {
        word.mark("Hard");
        assertEquals(0, word.getDifficulty());

        word.mark("Easy");
        assertEquals(1, word.getDifficulty());
        word.mark("Hard");
        word.mark("Hard");
        assertEquals(0, word.getDifficulty());
    }
}
