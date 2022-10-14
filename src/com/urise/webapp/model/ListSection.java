package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListSection implements Section {
    List<String> list = new ArrayList<>();

    ListSection(String...elements){
        list.addAll(Arrays.asList(elements));
    }
}
