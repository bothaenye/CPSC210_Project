package persistence;

import model.WordBank;
import model.Word;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// A large Portion of this code is based off of the JSONSERIALIZATIONDEMO
class TestReader {

    @Test
    void testReaderNonExistentFile() {
        Reader reader = new Reader("./data/noSuchFile.json");
        try {
            WordBank wr = reader.read();
            System.out.println(wr);
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWordBank() {
        Reader reader = new Reader("./data/testReaderEmptyWordBank.json");
        try {
            WordBank wr = reader.read();
            assertEquals(0, wr.getHardWords().size());
            assertEquals(0, wr.getEasyWords().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWordBank() {
        Reader reader = new Reader("./data/testWordBank.json");
        try {
            WordBank wr = reader.read();
            List<Word> words = wr.getHardWords();
            assertEquals(2, words.size());
            assertEquals(2, wr.getEasyWords().size());
            assertEquals("hello", words.get(0).getWord());
            assertEquals("world", words.get(1).getWord());
            assertEquals("tea", wr.getEasyWords().get(0).getWord());
            assertEquals("sugar", wr.getEasyWords().get(1).getWord());
            assertEquals(0, words.get(1).getDifficulty());
            assertEquals(1, wr.getEasyWords().get(1).getDifficulty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
