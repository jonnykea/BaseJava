package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    private final static int STORAGE_LIMIT = 1000;
    private int size;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("There isn't place to save");
        } else if (getIndex(r.getUuid()) > 0) {
            System.out.println("Resume have already existed");
        } else {
            storage[size++] = r;
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

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new IllegalArgumentException("Resume isn't found");
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}