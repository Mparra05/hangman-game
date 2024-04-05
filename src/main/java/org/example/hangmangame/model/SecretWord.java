package org.example.hangmangame.model;

import java.util.ArrayList;

public class SecretWord {

    private String secretWord;
    private ArrayList<String> secretWordSpaces;

    public SecretWord(String secretWord) {
        this.secretWord = secretWord.toUpperCase();
        secretWordSpaces = new ArrayList<String>();
        setSecretWordSpaces();
    }

    public String getSecretWord() {
        return this.secretWord;
    }

    public void setSecretWordSpaces() {
        for (byte i = 0; i < this.secretWord.length(); i++) {
            secretWordSpaces.add(String.valueOf(this.secretWord.charAt(i)));
        }
    }

    public ArrayList<String> getSecretWordSpaces() {
        return secretWordSpaces;
    }
}
