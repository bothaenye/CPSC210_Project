package ui;

import java.util.Scanner;
import model.Word;
import model.WordBank;
import persistence.Reader;
import persistence.Writer;

import java.io.FileNotFoundException;
import java.io.IOException;



// LanguageApp application
// Credit: Portions of this code (the structure) was taken from the TellerApp and the JSONSERIALIZATIONDEMO
public class LanguageApp {

    private static final String JSON_STORE = "./data/WordBank.json";
    private WordBank wordBank;
    private Scanner input;
    private Reader reader;
    private Writer writer;


    // EFFECTS: constructs wordBank and runs the language application
    public LanguageApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        wordBank = new WordBank();
        reader = new Reader(JSON_STORE);
        writer = new Writer(JSON_STORE);
        runLanguageApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLanguageApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nSee you Soon! Keep Learning!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("d")) {
            addWordUI();

        } else if (command.equals("w")) {
            testWordUI();

        } else if (command.equals("t")) {
            deleteWordUI();

        } else if (command.equals("e")) {
            markWordEasyUI();

        } else if (command.equals("f")) {
            viewProgressUI();

        } else if (command.equals("a")) {
            viewWordsUI();

        } else if (command.equals("s")) {
            searchWordUI();

        } else if (command.equals("k")) {
            loadWordBank();

        } else if (command.equals("o")) {
            saveWordBank();

        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the wordBank
    private void init() {
        wordBank = new WordBank();
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\td -> Enter new word");
        System.out.println("\tw -> Test random word");
        System.out.println("\tt -> Delete a word");
        System.out.println("\te -> Mark word as easy");
        System.out.println("\tf -> View progress");
        System.out.println("\ta -> View list of your words");
        System.out.println("\ts -> Search a word");
        System.out.println("\tk -> Load Your WordBank");
        System.out.println("\to -> Save Your WordBank");
        System.out.println("\tq -> quit");
    }

    //EFFECTS: adds a word to the list of hardWords
    //MODIFIES: this
    public void addWordUI() {
        System.out.println("What is the word?");
        String inputWord = input.next();

        System.out.println("What is the translation?");
        String inputTranslation = input.next();

        System.out.println("What is the Pronounciation?");
        String inputPronounciation = input.next();

        wordBank.addNewWord(inputWord, inputTranslation, inputPronounciation);
    }

    //EFFECTS: Displays a random word and asks the user if they would like to
    //         see the translation/pronounciation
    public void testWordUI() {
        Word word = wordBank.randomWord();
        System.out.println(word.getWord());

        System.out.println("would you like to see the translation and pronounciation? (y/n)");
        String answer = input.next();
        
        if (answer.equals("y")) {
            System.out.println("Translation: " + word.getTranslation());
            System.out.println("Pronounciation: " + word.getPronounciation());
        }
    }

    //EFFECTS: Deletes the a specified word from the hardWords list
    //MODIFIES: this
    public void deleteWordUI() {
        System.out.println("What word would you like to delete?");
        String answer = input.next();
        wordBank.deleteWord(answer);
        System.out.println("If the word was in the list, We have removed the word: " + answer);
    }

    //EFFECTS: moves a word to the easyWords list and marks the word difficulty as easy (1)
    //MODIFIES: this and a word (the input)
    //REQUIRES: answer is a word in the list of hardWords
    public void markWordEasyUI() {
        System.out.println("What word would you like to mark as easy?");
        String answer = input.next();
        wordBank.moveToEasyWords(answer);
        System.out.println("Congratulations on learning the word \"" + answer + "\"!");
    }

    //EFFECTS: Displays how many words have been learnt (marked as easy)
    public void viewProgressUI() {
        System.out.println("You have learnt " + wordBank.getSizeEasyWords() + " words!");
    }

    //EFFECTS: Display a list of words the user is still learning
    public void viewWordsUI() {
        System.out.println("You are still working on these words: \n" + wordBank.listOfWords());
    }

    //EFFECTS: displays the word, translation, and pronounciation of the word searched
    //REQUIRES: answer is a word in the list of hardWords
    public void searchWordUI() {
        System.out.println("What word would you like to search?");
        String answer = input.next();

        System.out.println(wordBank.searchWord(answer));
    }

    // MODIFIES: this
    // EFFECTS: loads wordBank from file
    private void loadWordBank() {
        try {
            wordBank = reader.read();
            System.out.println("Loaded WordBank from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves wordBank to file
    private void saveWordBank() {
        try {
            writer.open();
            writer.write(wordBank);
            writer.close();
            System.out.println("Saved wordBank to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}