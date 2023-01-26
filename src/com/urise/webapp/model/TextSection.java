package com.urise.webapp.model;

import static java.util.Objects.requireNonNull;

public class TextSection extends Section {
    private static final long serialVersionUID = 1L;

    public static final TextSection EMPTY = new TextSection("");

    private String content;

    public TextSection() {
    }

    public TextSection(String text) {
        requireNonNull(text, "text must not be null");
        this.content = text;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        /*return "-  " + content + "\n";*/
        return content;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;

        return content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}