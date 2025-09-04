package persistence;

import model.WordBank;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// A portion of this code is based off of the JSONSERIALIZATIONDEMO
class TestWriter extends TestWritable {

    @Test
    void testWriterInvalidFile() {
        try {
            WordBank wr = new WordBank();
            System.out.println(wr);
            Writer writer = new Writer("./data/my\\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            WordBank wr = new WordBank();
            Writer writer = new Writer("./data/testWriter.json");
            writer.open();
            writer.write(wr);
            writer.close();

            Reader reader = new Reader("./data/testWriter.json");
            wr = reader.read();
            assertEquals(0, wr.getHardWords().size());
            assertEquals(0, wr.getEasyWords().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            WordBank wr = new WordBank();
            wr.addNewWord("rain", "pluie", "plew-ee");
            wr.addNewWord("mouse", "souris", "soo-ree");
            wr.addNewWord("tea", "thé", "t-hey");
            wr.addNewWord("sugar", "sucre", "soo-kre");
            wr.moveToEasyWords("tea");
            wr.moveToEasyWords("sugar");
            Writer writer = new Writer("./data/testWriterGeneralWordBank.json");
            writer.open();
            writer.write(wr);
            writer.close();
            Reader reader = new Reader("./data/testWriterGeneralWordBank.json");
            wr = reader.read();
            assertEquals(2, wr.getHardWords().size());
            checkWord("rain", "pluie", "plew-ee",  wr.getHardWords().get(0));
            checkWord("mouse", "souris", "soo-ree", wr.getHardWords().get(1));  
            assertEquals(2, wr.getEasyWords().size());
            checkWord("tea", "thé", "t-hey",  wr.getEasyWords().get(0));
            checkWord("sugar", "sucre", "soo-kre", wr.getEasyWords().get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}