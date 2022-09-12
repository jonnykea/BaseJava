package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int NUM_OF_RESUMES = 1000;
    private int countResumes;
    private final Resume[] storage = new Resume[NUM_OF_RESUMES];

    public void clear() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }

    public void save(Resume r) {
        if (isFull()) {
            System.out.println("There isn't place to save");
        } else if (getIndex(r.getUuid()) > 0) {
            System.out.println("Resume have already existed");
        } else {
            storage[countResumes++] = r;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume isn't found");
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new IllegalArgumentException("Resume isn't found");
        }
        storage[index] = storage[countResumes - 1];
        storage[countResumes - 1] = null;
        countResumes--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResumes);
    }

    public int size() {
        return countResumes;
    }

    public boolean isFull() {
        return countResumes >= NUM_OF_RESUMES;
    }

    private int getIndex(String resume) {
        for (int i = 0; i < countResumes; i++) {
            if (resume.equals(storage[i].getUuid().toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
}