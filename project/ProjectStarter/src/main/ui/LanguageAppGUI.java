package ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Word;
import model.WordBank;
import model.EventLog;
import persistence.Reader;
import persistence.Writer;

import java.awt.Image;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;

public class LanguageAppGUI extends JFrame {

    private JLabel label;
    private JFrame frame;
    private JButton addWordButton;
    private JButton testWordButton;
    private JButton viewProgressButton;
    private JButton viewListButton;
    private JButton deleteWordButton;
    private JButton resetButton;
    private JButton searchButton;
    private JButton markEasyButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton enterButton;
    private JTextField textfieldWord;
    private JTextField textfieldTranslation;
    private JTextField textfieldPronouciation;
    private JTextField inputArea;
    private WordBank wordBank;
    private String input;
    private JLabel daisy;
    private JLabel labelDiv;
    private JLabel placeHolder;
    private static final String JSON_STORE = "./data/WordBank.json";
    private Reader reader;
    private Writer writer;
    private String state;
    private String word;
    private String pronounciation;
    private String translation;
    private Word randomWord;
    private JPanel panel;
    private GridBagConstraints constraints;

    public LanguageAppGUI() throws IOException, FileNotFoundException {

        //Initializing panel and frame
        init();

        //Adding components to the Panel
        addWordButton(panel, constraints);

        testWordButton(panel, constraints);

        viewProgressButton(panel, constraints);

        viewListButton(panel, constraints);

        deleteWordButton(panel, constraints);

        resetButton(panel, constraints);

        searchButton(panel, constraints);

        markEasyButton(panel, constraints);

        saveButton(panel, constraints);

        loadButton(panel, constraints);

        enterButton(panel, constraints);

        output();

        wordLabel();

        wordTextField();

        translationLabel();

        translationTextField();

        pronounciationLabel();

        pronounciationTextField();

        inputField();

        divider();

        placeHolder();

        //Adding the panel to the frame and displaying it
        setUpFrame();
    }


    //EFFECTS: Adding the panel to the frame and displaying it with modified properties
    //MODIFIES: frame
    private void setUpFrame() {
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Your Language Log!");
        frame.setSize(new Dimension(1000, 500));
        frame.setVisible(true);
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                EventLog eventLog = EventLog.getInstance();
                for (model.Event event : eventLog) {
                    System.out.println(event.toString());
                }
            }
        });
    }

    //EFFECTS: Making the Input Field
    //MODIFIES: panel
    private void inputField() {
        inputArea = new JTextField();
        textfieldPronouciation.setPreferredSize(new Dimension(60, 20));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 10;
        constraints.gridwidth = 15;
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(inputArea, constraints);
    }

    //EFFECTS: Making the pronouciation field for the word
    //MODIFIES: panel
    private void pronounciationTextField() {
        textfieldPronouciation = new JTextField();
        textfieldPronouciation.setPreferredSize(new Dimension(60, 20));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 10;
        constraints.gridx = 5;
        constraints.gridy = 5;
        panel.add(textfieldPronouciation, constraints);
    }

    //EFFECTS: Making the pronounciation label
    //MODIFIES: panel
    private void pronounciationLabel() {
        JLabel labelPronounciation = new JLabel("Pronounciation:"); 
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 4;
        constraints.gridy = 5;
        panel.add(labelPronounciation, constraints);
    }

    //EFFECTS: Making the translation field for the word
    //MODIFIES: panel
    private void translationTextField() {
        textfieldTranslation = new JTextField();
        textfieldTranslation.setPreferredSize(new Dimension(60, 20));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 10;
        constraints.gridx = 3;
        constraints.gridy = 5;
        panel.add(textfieldTranslation, constraints);
    }

    //EFFECTS: Making the translation label
    //MODIFIES: panel
    private void translationLabel() {
        JLabel labelTranslation = new JLabel("Translation:"); 
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 5;
        panel.add(labelTranslation, constraints);
    }

    //EFFECTS: Making the text field for the word
    //MODIFIES: panel
    private void wordTextField() {
        textfieldWord = new JTextField(""); 
        textfieldWord.setPreferredSize(new Dimension(60, 20));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipadx = 10;
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(textfieldWord, constraints);
    }

    //EFFECTS: Making the word label for the word text field
    //MODIFIES: panel
    private void wordLabel() {
        JLabel labelWord = new JLabel("Word:"); 
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(labelWord, constraints);
    }

    //EFFECTS: Making the output label
    //MODIFIES: panel, label
    private void output() {
        label = new JLabel("Output"); 
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 6;
        constraints.gridx = panel.getWidth();
        constraints.gridy = 0;
        Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        label.setBorder(border);
        panel.add(label, constraints);
    }

    //EFFECTS: Making a space placeholder to correctly position the buttons
    //MODIFIES: panel, placeholder
    private void placeHolder() {
        placeHolder = new JLabel(""); 
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 4;
        constraints.gridy = 2;
        Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        placeHolder.setBorder(border);
        panel.add(placeHolder, constraints);
    }

    //EFFECTS: Making a divider between the Word input fields and the buttons
    //MODIFIES: panel, labelDiv
    private void divider() {
        labelDiv = new JLabel(""); 
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 6;
        constraints.gridx = panel.getWidth();
        constraints.gridy = 4;
        Border border = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        labelDiv.setBorder(border);
        labelDiv.setBackground(new Color(255, 255, 179));
        panel.add(labelDiv, constraints);
    }

    //EFFECTS: Making/Initializing the wordbank, frame, image, and panel
    //MODIFIES: panel
    private void init() throws IOException {
        wordBank = new WordBank(); // make the wordbank
        frame = new JFrame(); // make window
        panel = new JPanel(); // make the panel
        constraints = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(new Color(255, 255, 179));

        reader = new Reader(JSON_STORE);
        writer = new Writer(JSON_STORE);

        BufferedImage daisyImgBuffered = ImageIO.read(new File("data/daisy.png"));
        Image daisyIMG = daisyImgBuffered.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(daisyIMG);
        daisy = new JLabel(icon);
        constraints.gridy = 0;
        constraints.gridx = 6;
        panel.add(daisy, constraints);
        daisy.setVisible(false);
    }

    //EFFECTS: Making the enterButton
    //MODIFIES: panel, enterButton
    private void enterButton(JPanel panel, GridBagConstraints c) {
        enterButton = new JButton(new EnterListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 3;
        enterButton.setBackground(new Color(217, 147, 117));
        enterButton.setForeground(Color.WHITE);
        panel.add(enterButton, c);
    }

    //EFFECTS: Making the load Button
    //MODIFIES: panel, loadButton
    private void loadButton(JPanel panel, GridBagConstraints c) {
        loadButton = new JButton(new LoadListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 6;
        c.gridy = 2;
        loadButton.setBackground(new Color(153, 102, 51));
        loadButton.setForeground(Color.WHITE);
        panel.add(loadButton, c);
    }

    //EFFECTS: Making the save Button
    //MODIFIES: panel, saveButton
    private void saveButton(JPanel panel, GridBagConstraints c) {
        saveButton = new JButton(new SaveListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 6;
        c.gridy = 3;
        saveButton.setBackground(new Color(153, 102, 51));
        saveButton.setForeground(Color.WHITE);
        panel.add(saveButton, c);
    }

    //EFFECTS: Making the mark Easy Button
    //MODIFIES: panel, markEasyButton
    private void markEasyButton(JPanel panel, GridBagConstraints c) {
        markEasyButton = new JButton(new MarkEasyListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        markEasyButton.setBackground(new Color(77, 38, 0));
        markEasyButton.setForeground(Color.WHITE);
        panel.add(markEasyButton, c);
    }

    //EFFECTS: Making the search Button
    //MODIFIES: panel, searchButton
    private void searchButton(JPanel panel, GridBagConstraints c) {
        searchButton = new JButton(new SearchListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        searchButton.setBackground(new Color(77, 38, 0));
        searchButton.setForeground(Color.WHITE);
        panel.add(searchButton, c);
    }

    //EFFECTS: Making the reset Button
    //MODIFIES: panel, resetButton
    private void resetButton(JPanel panel, GridBagConstraints c) {
        resetButton = new JButton(new ResetListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        resetButton.setBackground(new Color(77, 38, 0));
        resetButton.setForeground(Color.WHITE);
        panel.add(resetButton, c);
    }

    //EFFECTS: Making the delete Word Button
    //MODIFIES: panel, deleteWordButton
    private void deleteWordButton(JPanel panel, GridBagConstraints c) {
        deleteWordButton = new JButton(new DeleteWordListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 3;
        deleteWordButton.setBackground(new Color(77, 38, 0));
        deleteWordButton.setForeground(Color.WHITE);
        panel.add(deleteWordButton, c);
    }

    //EFFECTS: Making the view List Button
    //MODIFIES: panel, viewListButton
    private void viewListButton(JPanel panel, GridBagConstraints c) {
        viewListButton = new JButton(new ViewListListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 2;
        viewListButton.setBackground(new Color(77, 38, 0));
        viewListButton.setForeground(Color.WHITE);
        panel.add(viewListButton, c);
    }

    //EFFECTS: Making the view Progress Button
    //MODIFIES: panel, viewProgressButton
    private void viewProgressButton(JPanel panel, GridBagConstraints c) {
        viewProgressButton = new JButton(new ViewProgressListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        viewProgressButton.setBackground(new Color(77, 38, 0));
        viewProgressButton.setForeground(Color.WHITE);
        panel.add(viewProgressButton, c);
    }

    //EFFECTS: Making the test Word Button
    //MODIFIES: panel, testWordButton
    private void testWordButton(JPanel panel, GridBagConstraints c) {
        testWordButton = new JButton(new TestWordListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        testWordButton.setBackground(new Color(77, 38, 0));
        testWordButton.setForeground(Color.WHITE);
        panel.add(testWordButton, c);
    }

    //Making the add Word Button
    //MODIFIES: panel, addWordButton
    private void addWordButton(JPanel panel, GridBagConstraints c) {
        addWordButton = new JButton(new AddWordListener());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 6;
        c.gridy = 5;
        addWordButton.setBackground(new Color(153, 102, 51));
        addWordButton.setForeground(Color.WHITE);
        panel.add(addWordButton, c);
    }

    //EFFECTS: Making the AddWordListener to perform the addWord Button functionality
    private class AddWordListener extends AbstractAction {

        AddWordListener() {
            super("Add Word");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            daisy.setVisible(true);

            word = textfieldWord.getText();
            translation = textfieldTranslation.getText();
            pronounciation = textfieldPronouciation.getText();

            if (word.equals("")) {
                label.setText("No New Word Added, Enter Word In Boxes Below.");
            } else {
                wordBank.addNewWord(word, translation, pronounciation);
                label.setText("You've added: \"" + word + "\" to your log!");
            }
            resetAllButOutput();
        }
    }

    //EFFECTS: Making the TestWordListener to perform the testWord Button functionality
    private class TestWordListener extends AbstractAction {

        TestWordListener() {
            super("Test Word");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            daisy.setVisible(false);
            state = "testState";
            randomWord = wordBank.randomWord();
            label.setText(
                     "\"" + randomWord.getWord() 
                     + "\", would you like to see the translation and pronounciation? (y/n)");
        }
    }

    //EFFECTS: Making the ViewProgressListener to perform the viewProgress Button functionality
    private class ViewProgressListener extends AbstractAction {

        ViewProgressListener() {
            super("View Progress");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            daisy.setVisible(false);
            label.setText("You have learnt " + wordBank.getSizeEasyWords() + " words!");
            resetAllButOutput();
        }
    }

    //EFFECTS: Making the viewListListener to perform the viewList Button functionality
    private class ViewListListener extends AbstractAction {

        ViewListListener() {
            super("View List");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            daisy.setVisible(false);
            label.setText("You are still working on these words: \n" + wordBank.listOfWords());
            resetAllButOutput();
        }
    }

    //EFFECTS: Making the DeleteWordListener to perform the deleteWord Button functionality
    private class DeleteWordListener extends AbstractAction {

        DeleteWordListener() {
            super("Delete Word");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            daisy.setVisible(false);
            label.setText("What word would you like to delete?");
            state = "deleteState";
        }
    }

    //EFFECTS: Making the ResetListener to perform the reset Button functionality
    private class ResetListener extends AbstractAction {

        ResetListener() {
            super("Reset");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            daisy.setVisible(false);
            label.setText("Output");
            resetAllButOutput();
        }
    }

    //EFFECTS: Making the SearchListener to perform the searchWord Button functionality
    private class SearchListener extends AbstractAction {

        SearchListener() {
            super("Search Word");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            daisy.setVisible(false);
            input = inputArea.getText();
            label.setText("What word would you like to search?");
            state = "searchState";
        }
    }

    //EFFECTS: Making the MarkEasyListener to perform the markEasy Button functionality
    private class MarkEasyListener extends AbstractAction {

        MarkEasyListener() {
            super("Mark Easy");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            daisy.setVisible(false);
            input = inputArea.getText();
            label.setText("What word would you like to mark as easy?");
            state = "markEasyState";
        }
    }

    //EFFECTS: Making the SaveListener to perform the save Button functionality
    private class SaveListener extends AbstractAction {

        SaveListener() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            daisy.setVisible(false);
            try {
                writer.open();
                writer.write(wordBank);
                writer.close();
                label.setText("Saved wordBank to " + JSON_STORE);
            } catch (FileNotFoundException ee) {
                label.setText("Unable to write to file: " + JSON_STORE);
            }
            resetAllButOutput();
        }
    }

    //EFFECTS: Making the loadListener to perform the load Button functionality
    private class LoadListener extends AbstractAction {

        LoadListener() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            daisy.setVisible(false);
            try {
                wordBank = reader.read();
                label.setText("Loaded WordBank from " + JSON_STORE);
            } catch (IOException ee) {
                label.setText("Unable to read from file: " + JSON_STORE);
            }
            resetAllButOutput();
        }
    }

    //EFFECTS: Making the EnterListener to perform the enter Button functionality
    private class EnterListener extends AbstractAction {

        EnterListener() {
            super("Enter");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!inputArea.getText().equals("")) {
                input = inputArea.getText();
                switch (state) {
                    case "testState":
                        testState(); //operations for the testWordButton when the enters inputs a valid input
                        break;
                    case "searchState":
                        searchState(); //operations for the searchWordButton when the enters inputs a valid input
                        break;

                    case "markEasyState":
                        markEasyState(); //operations for the markEasyButton when the enters inputs a valid input
                        break;

                    case "deleteState":
                        deleteState(); //operations for the deleteWordButton when the enters inputs a valid input
                        break;

                    default:
                        break;
                }
            }
        }

        //HELPER FUNCTIONS FOR THE EnterListener CLASS ABOVE 
        //(perform these functions with valid input - check valid input when enter button pressed)
        private void testState() {
            if (input.equals("y")) {
                label.setText("Translation: " + randomWord.getTranslation() + "      Pronounciation: "
                        + randomWord.getPronounciation());
                resetAllButOutput();
            } else if (input.equals("n")) {
                label.setText("Output");
                resetAllButOutput();
            }
        }

        private void searchState() {
            if (!input.equals("")) {
                label.setText(wordBank.searchWord(input));
                resetAllButOutput();
            }
        }

        private void markEasyState() {
            if (!input.equals("")) {
                wordBank.moveToEasyWords(input);
                label.setText("Congratulations on learning the word \"" + input + "\"!");
                resetAllButOutput();
            }
        }

        private void deleteState() {
            if (!input.equals("")) {
                wordBank.deleteWord(input);
                label.setText("If the word was in the list, We have removed the word: " + input);
                resetAllButOutput();
            }
        }
    }

    //EFFECTS: Reset the state of the application/fields
    //MODIFIES: inputArea, textfieldPronouciation, textfieldTranslation, textfieldWord
    public void resetAllButOutput() {
        inputArea.setText("");
        textfieldPronouciation.setText("");
        textfieldTranslation.setText("");
        textfieldWord.setText("");
    }

}