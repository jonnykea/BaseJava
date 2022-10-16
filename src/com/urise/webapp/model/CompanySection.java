package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompanySection extends Section {

    List<Company> company = new ArrayList<>();

    public CompanySection(Company... elements) {
        company.addAll(Arrays.asList(elements));
    }

    public List<Company> getCompany() {
        return company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompanySection that = (CompanySection) o;

        return company != null ? company.equals(that.company) : that.company == null;
    }

    @Override
    public int hashCode() {
        return company != null ? company.hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Company c : company) {
            sb.append(c);
        }
        return sb.toString();
    }
}
