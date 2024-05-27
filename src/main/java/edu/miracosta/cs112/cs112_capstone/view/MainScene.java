package edu.miracosta.cs112.cs112_capstone.view;

import edu.miracosta.cs112.cs112_capstone.model.Dictionary;
import edu.miracosta.cs112.cs112_capstone.model.Language;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class MainScene extends Scene {
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;

    private final TextArea textArea = new TextArea();
    private final TextArea badWordArea = new TextArea();
    private final TextField titleField = new TextField("Title");
    private final Button spellCheckButton = new Button("Check Spelling");
    private final Button saveButton = new Button("Save");
    private final Button openButton = new Button("Open");
    private final Label errorLabel = new Label("Typos:");
    private final Button wordCountButton = new Button("Check Word Count");
    private final Label wordCount = new Label();
    private Spellchecker spellchecker;
    private WordCounter wordcounter;

    private final Dictionary DICTIONARY = new Dictionary("src\\words_alpha.txt", Language.ENGLISH);
//    private final File DICTIONARY = new File("words_alpha.txt");

    public MainScene() {
        super(new GridPane());

        spellCheckButton.setOnAction(e -> checkCurrentSpelling());
        openButton.setOnAction(e -> openDoc());
        saveButton.setOnAction(e -> saveDoc());
        wordCountButton.setOnAction(e -> countWords());

        textArea.setPrefWidth(WIDTH);
        textArea.setPrefHeight(HEIGHT);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(2);

        gridPane.add(textArea,0,1);
        gridPane.add(errorLabel, 0, 2);
        badWordArea.setEditable(false);
        badWordArea.setMouseTransparent(true);
        badWordArea.setFocusTraversable(false);
        GridPane.setFillWidth(textArea, true);
        gridPane.add(badWordArea,0,3);

        GridPane headerPane = new GridPane();
        headerPane.setHgap(2);
        gridPane.add(headerPane,0,0);

        headerPane.add(titleField, 0,0);
        headerPane.add(openButton,1,0);
        headerPane.add(saveButton,2,0);
        headerPane.add(spellCheckButton,3,0);
        headerPane.add(wordCountButton, 4,0);
        headerPane.add(wordCount,5,0);

        this.setRoot(gridPane);
    }

    private void countWords() {
        wordcounter = new WordCounter(textArea);
        wordCount.setText("Word Count: " + Integer.toString(wordcounter.getWordCount()));
    }

    private void checkCurrentSpelling() {
        spellchecker = new Spellchecker(DICTIONARY, textArea);
        StringBuilder badWordList = new StringBuilder();
        ArrayList<String> noMatches = spellchecker.checkSpelling();
        for (String noMatch : noMatches) {
            badWordList.append(noMatch).append(" ");
        }
        badWordArea.setText(badWordList.toString());
    }

    private void saveDoc() {
        String textAreaContent = textArea.getText();

        // Create a JFrame to attach the JFileChooser to
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Create a JFileChooser to show the save dialog
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Binary File");

        int userSelection = fileChooser.showSaveDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            try (FileOutputStream fileOutputStream = new FileOutputStream(fileToSave)) {
                // Convert the text string to bytes
                byte[] textBytes = textAreaContent.getBytes();

                // Write the bytes to the binary file
                fileOutputStream.write(textBytes);

                System.out.println("Successfully written to the binary file.");
            } catch (IOException e) {
                System.err.println("An IOException occurred: " + e.getMessage());
            }
        }

        // Close the frame
        frame.dispose();
    }

    private void openDoc() {
        // Create a JFrame to attach the JFileChooser to
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        StringBuilder content = new StringBuilder();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Binary File");

        // Show open file dialog
        var file = fileChooser.showOpenDialog(null);

        if (file == JFileChooser.APPROVE_OPTION) {
            try (FileInputStream fileInputStream = new FileInputStream(fileChooser.getSelectedFile().toString())) {
                int ch;
                while ((ch = fileInputStream.read()) != -1) {
                    content.append((char) ch);
                }
                textArea.setText(content.toString());
            } catch (IOException e) {
                System.err.println("An IOException occurred: " + e.getMessage());
            }
        }
    }
}
