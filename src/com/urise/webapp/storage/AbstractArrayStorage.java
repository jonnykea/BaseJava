package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10000;

    protected int size;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public final int size() {
        return size;
    }

    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume isn't found");
        } else {
            storage[index] = r;
        }
    }

    public final void save(Resume r) {
        if (size == STORAGE_LIMIT) {
            System.out.println("There isn't place to save");
        } else if (getIndex(r.getUuid()) >= 0) {
            System.out.println("Resume already exist");
        } else {
            saveResume(r);
            size++;
        }
    }

    public final void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new IllegalArgumentException("Resume isn't found");
        }
        deleteResume(index);
        storage[size - 1] = null;
        size--;
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            return null;
        }
        return storage[index];
    }

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract void deleteResume(int index);

    protected abstract void saveResume(Resume r);

    protected abstract int getIndex(String uuid);
}
