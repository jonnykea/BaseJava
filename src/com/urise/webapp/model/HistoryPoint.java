package com.urise.webapp.model;

import java.time.LocalDate;

public class HistoryPoint implements Section{
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String organizationName;
    private final String reference;
    private final String position;
    private final String experience;

    public HistoryPoint(LocalDate startDate, LocalDate endDate, String organizationName,
                        String reference, String position, String experience) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.organizationName = organizationName;
        this.reference = reference;
        this.position = position;
        this.experience = experience;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getReference() {
        return reference;
    }

    public String getPosition() {
        return position;
    }

    public String getExperience() {
        return experience;
    }
}