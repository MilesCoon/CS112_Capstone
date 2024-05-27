package edu.miracosta.cs112.cs112_capstone.view;

import edu.miracosta.cs112.cs112_capstone.model.Dictionary;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class Spellchecker extends Checker {

    private Dictionary DICTIONARY;
    // Populated Constructor using Dictionary
    Spellchecker(Dictionary dictionary, TextArea textArea) {
        super(textArea);
        this.DICTIONARY = dictionary;
    }

    public ArrayList<String> checkSpelling() {
        // Grab all text from textArea
        var wordList = getWordList();
        // Old Bad Word Box
        var noMatches = new ArrayList<String>();
        for (int i = 0; i < wordList.length; i++) {
            if (!DICTIONARY.getDictionaryList().contains(wordList[i])) {
                noMatches.add(wordList[i]);
            }
            // noMatches.add(DICTIONARY.getDictionaryList().stream().filter(it -> !it.contains(textAreaList[i])).collect(Collectors.toList()));
        }
        return noMatches;
    }

}
