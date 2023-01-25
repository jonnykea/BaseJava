package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ListSection extends Section {
    private static final long serialVersionUID = 1L;

    public static final ListSection EMPTY = new ListSection("");

    private List<String> items = new ArrayList<>();

    public ListSection() {
    }

    public ListSection(String... elements) {
        requireNonNull(items, "items must not be null");
        items.addAll(Arrays.asList(elements));
    }

    public ListSection(List<String> list) {
        requireNonNull(items, "items must not be null");
        this.items = list;
    }

    public List<String> getItems() {
        return items;
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