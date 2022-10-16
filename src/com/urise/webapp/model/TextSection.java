package com.urise.webapp.model;

public class TextSection implements Section {

    String text;

    public TextSection(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text + "\n";
    }
}
