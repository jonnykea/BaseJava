package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompanySection implements Section{

    List<Company> company = new ArrayList<>();

    public CompanySection(Company... elements){
        company.addAll(Arrays.asList(elements));
    }

    public List<Company> getCompany(){
        return company;
    }
}
