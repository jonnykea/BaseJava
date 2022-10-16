package com.urise.webapp.model;

import java.util.List;

public class Company extends Section {

    private final String name;
    private final List<Period> periods;

    public String getName() {
        return name;
    }

    public Company(String name, List<Period> periods) {
        this.name = name;
        this.periods = periods;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        for (Period p : periods) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (name != null ? !name.equals(company.name) : company.name != null) return false;
        return periods != null ? periods.equals(company.periods) : company.periods == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (periods != null ? periods.hashCode() : 0);
        return result;
    }
}