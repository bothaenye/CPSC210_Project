package persistence;

import model.WordBank;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// A large Portion of this code is based off of the JSONSERIALIZATIONDEMO
// Represents a reader that reads wordBank from JSON data stored in file
public class Reader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public Reader(String source) {
        this.source = source;
    }

    // EFFECTS: reads wordBank from file and returns it;
    // throws IOException if an error occurs reading data from file
    public WordBank read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWordBank(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses WordBank from JSON object and returns it
    private WordBank parseWordBank(JSONObject jsonObject) {
        WordBank wr = new WordBank();
        addWords(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses words from JSON object and adds them to wordBank
    private void addWords(WordBank wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("hardWords");
        JSONArray jsonArray2 = jsonObject.getJSONArray("easyWords");
        for (Object json : jsonArray) {
            JSONObject nextWord = (JSONObject) json;
            addWord(wr, nextWord);
        }
        for (Object json : jsonArray2) {
            JSONObject nextWord2 = (JSONObject) json;
            addWord(wr, nextWord2);
            wr.moveToEasyWords(nextWord2.getString("word"));
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses word from JSON object and adds it to wordBank
    private void addWord(WordBank wr, JSONObject jsonObject) {
        String name = jsonObject.getString("word");
        String translation = jsonObject.getString("translation");
        String pronounciation = jsonObject.getString("pronounciation");
        wr.addNewWord(name, translation, pronounciation);
    }
}
