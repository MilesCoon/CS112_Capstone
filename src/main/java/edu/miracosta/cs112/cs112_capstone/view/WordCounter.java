package edu.miracosta.cs112.cs112_capstone.view;

import javafx.scene.control.TextArea;

public class WordCounter extends Checker {
    protected int wordCount = 0;
    WordCounter(TextArea textArea) {
        super(textArea);
    }

    int getWordCount() {
        // Get list of words
        var wordList = getWordList();
        // Loop through each index of word list and increase wordCount by 1
        for (var word : wordList ) {
            wordCount++;
        }
        return wordCount;
    }
}
