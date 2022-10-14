package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistorySection implements Section{
    private final List<HistoryPoint> organizations = new ArrayList<>();

    HistorySection(HistoryPoint...elements){
        organizations.addAll(Arrays.asList(elements));
    }
}
