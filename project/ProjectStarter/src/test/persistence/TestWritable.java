package persistence;

import model.Word;

import static org.junit.jupiter.api.Assertions.assertEquals;

// A large portion of this code is based off of the JSONSERIALIZATIONDEMO
public class TestWritable {
    protected void checkWord(String word, String translation, String pronounciation, Word wordToTest) {
        assertEquals(word, wordToTest.getWord());
        assertEquals(translation, wordToTest.getTranslation());
        assertEquals(pronounciation, wordToTest.getPronounciation());
    }
}
