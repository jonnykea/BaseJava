package com.urise.webapp.model;

public class Company implements Section {

    String name;
    Period period;
    String position;

    public Company(String name, Period period) {
        this.name = name;
        this.period = period;
    }

    public Company(String name, String position, Period period) {
        this.name = name;
        this.position = position;
        this.period = period;
    }

    @Override
    public String toString() {
        return period + "\n" + name + "\n" +
                position + "\n";
    }
}