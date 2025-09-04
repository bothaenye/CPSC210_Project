package model;

import org.json.JSONObject;
import persistence.Writable;

// A portion of this code is based off of the JSONSERIALIZATIONDEMO
// Represents a word with 3 fields of type string for the word, its tanslation, and its pronounciation.
// Also has an integer field (0/1) representing if the word is difficult or easy.
public class Word implements Writable {
    
    private String word; 
    private String pronounciation;
    private String translation;
    private Integer difficulty;

    // EFFECTS: represents a class with a string for the word in the user's native language, 
    //          a string for the pronounciation of the word in the target language, and another
    //          string for the translation of the word into the target language. Finally, with an 
    //          integer (1/0) to represent if the word is easy or hard for the user (0: Hard, 1:Easy)
    public Word(String wordInput, String translationInput, String pronounciationInput) {
        word = wordInput;
        pronounciation = pronounciationInput;
        translation = translationInput;
        difficulty = 0; //0 represents hard and 1 represents easy
        EventLog.getInstance().logEvent(new Event("created a new word"));
    }

    public String getWord() {
        return (this.word);
    }

    public String getPronounciation() {
        return (this.pronounciation);
    }

    public String getTranslation() {
        return (this.translation);
    }

    public Integer getDifficulty() {
        return (this.difficulty);
    }

    // MODIFIES: this
    // EFFECTS: Mark the word as a easy/hard word depending on parameter
    public void mark(String difficulty) {
        if (difficulty.equals("Easy")) {
            this.difficulty = 1;
        } else {
            this.difficulty = 0;
        }
        EventLog.getInstance().logEvent(new Event("marked word as easy!"));
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("word", word);
        json.put("translation", translation);
        json.put("pronounciation", pronounciation);
        EventLog.getInstance().logEvent(new Event("converted word to JSON object"));
        return json;
    }
}
