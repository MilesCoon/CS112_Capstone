package edu.miracosta.cs112.cs112_capstone.view;

import javafx.scene.control.TextArea;

public abstract class Checker {
    private TextArea textArea;

    Checker(TextArea textArea) {
        this.textArea = textArea;
    }
    // Returns a list of parsed string words from textArea
    protected String[] getWordList() {
        // Take text area content with .getText()
        // Then replace all punctuation with a blank space with .replaceAll() and a regex for punctuation ("\\p{Punct}")
        // Now that the string is free of any unnecessary punctuation, make everything lowercase
        // Now, split the String into a list at every space " "
        // And now we can return this list of every word in the text area
        return textArea.getText().replaceAll("\\p{Punct}", "").toLowerCase().split(" ");
    }


}
