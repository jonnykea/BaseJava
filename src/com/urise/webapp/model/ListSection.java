package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ListSection extends AbstractSection {

    private final List<String> items = new ArrayList<>();

    public ListSection(String... elements) {
        requireNonNull(items, "items must not be null");
        items.addAll(Arrays.asList(elements));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : items) {
            sb.append("-  ").append(s).append("\n");
        }
        return sb + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListSection that = (ListSection) o;

        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }
}