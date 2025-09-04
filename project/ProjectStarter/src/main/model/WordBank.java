package model;

import java.util.ArrayList;
import java.util.Random;

import persistence.Writable;

import org.json.JSONArray;
import org.json.JSONObject;

// A portion of this code is based off of the JSONSERIALIZATIONDEMO
// Represents a WordBank having an ArrayList containing words that the user finds harder, 
// and another ArrayList of words that the user finds easier
public class WordBank implements Writable {
    private ArrayList<Word> hardWords;  // tracks words the user is learning
    private ArrayList<Word> easyWords;  // tracks words the user has learnt

    // EFFECTS: represents a class with a list of words for the hard Words (still learning)
    //          and a list of words for the easy words (already learnt).
    public WordBank() {
        hardWords = new ArrayList<Word>();
        easyWords = new ArrayList<Word>();
    }

    public ArrayList<Word> getHardWords() {
        return (this.hardWords);
    }

    public ArrayList<Word> getEasyWords() {
        return (this.easyWords);
    }

    // EFFECTS: retreives a word from the list with this.word equal to the input word, 
    //          and outputs the corresponding word object
    public Word getWordFromList(String word, ArrayList<Word> list) {
        int i = 0; 
        while (i < list.size()) {
            if (word.equals(list.get(i).getWord())) {
                return (list.get(i));
            }
            i++;
        }
        EventLog.getInstance().logEvent(new Event("retrieved a word from the word log"));
        return (null);
    }

    // EFFECTS: returns how many words the user has learnt
    public Integer getSizeEasyWords() {
        return (this.easyWords.size());
    }

    // REQUIRES: word, translation, and pronounciation have non-zero lengths
    // MODIFIES: this
    // EFFECTS: hardWords appends a new word to the list of words
    public void addNewWord(String word, String translation, String pronounciation) {
        this.hardWords.add(new Word(word, translation, pronounciation));
        EventLog.getInstance().logEvent(new Event("added a new word"));
    }

    // REQUIRES: wordToMove has a non-zero length and is in hardWords
    // MODIFIES: this
    // EFFECTS: moves the word from the hardWords list to the easyWords list
    public void moveToEasyWords(String wordToMove) {
        final Word toMove = this.getWordFromList(wordToMove, this.getHardWords());
        toMove.mark("Easy");
        this.easyWords.add(toMove);
        this.deleteWord(wordToMove);
        //Uncomment Line below for repetition - however redundant message
        //EventLog.getInstance().logEvent(new Event("marked word as easy and removed it from the word log"));
    }

    //EFFECTS: Returns a random word from the hardWords list (for review purposes)
    public Word randomWord() {
        Random rand = new Random(); 
        int intRandom = rand.nextInt(this.hardWords.size());

        EventLog.getInstance().logEvent(new Event("returned a random word"));
        return (this.hardWords.get(intRandom));
    }

    // REQUIRES: wordToDelete has a non-zero length
    // MODIFIES: this
    // EFFECTS: deletes the word with name equal to wordToDelete from the hardWords list 
    public void deleteWord(String wordToDelete) { 
        for (int i = 0; i < this.hardWords.size(); i++) {
            if (wordToDelete.equals(this.hardWords.get(i).getWord())) {
                this.hardWords.remove(this.hardWords.get(i));
            }
        }
        EventLog.getInstance().logEvent(new Event("deleted a word from word log"));
    }

    // REQUIRES: word has a non-zero length
    // EFFECTS: find the word with this.word equal to word and returns the word, its translation, and its pronounciation
    public String searchWord(String word) {
        int i = 0; 
        EventLog.getInstance().logEvent(new Event("searched a word from the word log"));

        while (i < this.hardWords.size()) {
            if (word.equals(this.hardWords.get(i).getWord())) {
                return ("Word: " + word + ", Translation: " + this.hardWords.get(i).getTranslation() 
                        + ", Pronounciation: " + this.hardWords.get(i).getPronounciation());
            }
            i++;
        }
        return ("That word is not logged.");
    }

    // EFFECTS: converts the hardWords list to a list of strings of the word fields
    public ArrayList<String> listOfWords() {
        final ArrayList<String> wordsList = new ArrayList<String>();
        EventLog.getInstance().logEvent(new Event("converted log to list of words in string format"));

        for (int i = 0; i < this.hardWords.size(); i++) {
            wordsList.add(this.hardWords.get(i).getWord());
        }
        return (wordsList);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("easyWords", easyWordsToJson());
        json.put("hardWords", hardWordsToJson());

        EventLog.getInstance().logEvent(new Event("added word log to JSON object"));
        EventLog.getInstance().logEvent(new Event("added words learnt to JSON object"));
        
        return json;
    }

    // EFFECTS: returns words for hardWords in this wordBank as a JSON array
    private JSONArray hardWordsToJson() {
        EventLog.getInstance().logEvent(new Event("converted word log to JSON array"));

        JSONArray jsonArray = new JSONArray();

        for (Word w : hardWords) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns words for easyWords in this wordBank as a JSON array
    private JSONArray easyWordsToJson() {
        EventLog.getInstance().logEvent(new Event("converted learnt words log to JSON array"));

        JSONArray jsonArray = new JSONArray();

        for (Word w : easyWords) {
            jsonArray.put(w.toJson());
        }

        return jsonArray;
    }
    
}
