package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class CompanySection extends Section {
    private static final long serialVersionUID = 1L;

    private List<Company> companies = new ArrayList<>();

    public CompanySection() {
    }

    public CompanySection(Company... elements) {
        requireNonNull(companies, "companies must not be null");
        companies.addAll(Arrays.asList(elements));
    }

    public CompanySection(List<Company> companies) {
        requireNonNull(companies, "companies must not be null");
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Company c : companies) {
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return companies.equals(that.companies);
    }

    @Override
    public int hashCode() {
        return companies.hashCode();
    }
}