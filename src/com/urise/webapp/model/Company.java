package com.urise.webapp.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Company extends Section {
    private static final long serialVersionUID = 1L;

    public static final Company EMPTY = new Company("","", Collections.singletonList(Period.EMPTY));

    private String name;

    private String website;

    private List<Period> periods;

    public Company() {
    }

    public Company(String name, String website, List<Period> periods) {
        this.name = name;
        this.website = website == null ? "" : website;
        this.periods = periods;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        this.website = website == null ? "" : website;
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" - ").append(website).append("\n");
        for (Period p : periods) {
            sb.append(p);
        }
        return sb + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!Objects.equals(name, company.name)) return false;
        return Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (periods != null ? periods.hashCode() : 0);
        return result;
    }
}