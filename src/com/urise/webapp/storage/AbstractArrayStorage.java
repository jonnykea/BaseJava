package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10000;

    protected int size;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);
}
