package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;

public class ListStorageTest {
    private final static ListStorage listResume = new ListStorage();
    public static void main(String[] args) {
        final String UUID_1 = "uuid1";
        final String UUID_2 = "uuid2";
        final String UUID_3 = "uuid3";
        final String UUID_4 = "uuid4";
        final String UUID_NOT_EXIST = "dummy";

        final Resume RESUME_1 = new Resume(UUID_1);
        final Resume RESUME_2 = new Resume(UUID_2);
        final Resume RESUME_3 = new Resume(UUID_3);
        final Resume RESUME_4 = new Resume(UUID_4);


    }
}
