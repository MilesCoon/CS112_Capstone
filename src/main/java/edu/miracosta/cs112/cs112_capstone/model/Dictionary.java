package edu.miracosta.cs112.cs112_capstone.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Dictionary implements DictionaryInterface {

    private List<String> dictionaryList = new ArrayList<>();
    private Language languageType;

    public List<String> getDictionaryList() {
        return dictionaryList;
    }

    public void setDictionaryList(List<String> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }

    public Dictionary(String dictionaryFolder, Language dictionaryLanguage) {
        File dir = new File(dictionaryFolder);
        languageType = dictionaryLanguage;
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // Do something with child
                try (Stream<String> lines = Files.lines(Paths.get(child.toString()))) {
                    dictionaryList.addAll(lines.toList());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public Language getLanguageType() {
        return languageType;
    }

    public void setLanguageType(Language languageType) {
        this.languageType = languageType;
    }

    @Override
    public int wordCount() {
        return getDictionaryList().size();
    }

    @Override
    public Language languageType() {
        return getLanguageType();
    }
}
