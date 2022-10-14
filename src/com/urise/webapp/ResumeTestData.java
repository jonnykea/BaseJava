package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.util.HashMap;

public class ResumeTestData {
    public static void main(String[] args) {


        Resume resume = new Resume("1", "Григорий Кислин", new HashMap<>(),
                new HashMap<>());
    }
}
