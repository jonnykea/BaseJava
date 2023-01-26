package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
//        items.removeIf(s -> s == null || "".equals(s));
              return items.stream()
                      .filter(s -> (s != null) && (!s.trim().isEmpty())&& (!s.equals("\n")))
                      .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String s : items) {
//            sb.append("-  ").append(s).append("\n");
            sb.append(s).append("\n");
        }
        return sb + "\n";
//        return items.toString();
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