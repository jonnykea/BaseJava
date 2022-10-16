package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Period {
    private final String startDate;
    private final String endDate;
    private final String description;

    public Period(String startDate, String endDate, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        List<String> period = new ArrayList<>();
        period.addAll(Arrays.asList(startDate,endDate,description));
    }

    @Override
    public String toString() {
        return startDate + endDate + "\n" +
                description;
    }
}
