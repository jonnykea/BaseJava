package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected final static int STORAGE_LIMIT = 10000;

    protected int size;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];

    public final int size() {
        return size;
    }

    public final void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            storage[index] = r;
        }
    }

    public final void delete(String uuid) {
        if (!contains(new Resume(uuid))) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(uuid);
    }

    public final Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return returnResume(uuid);
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

    protected final boolean isOverFlow() {
        return size == STORAGE_LIMIT;
    }

    protected final boolean contains(Resume r) {
        return getIndex(r.getUuid()) > -1;
    }

    protected final void store(Resume r) {
        saveResume(r);
        size++;
    }

    protected void deleteResume(String uuid) {
        deleteResume(getIndex(uuid));
        storage[size - 1] = null;
        size--;
    }

    protected Resume returnResume(String uuid) {
        return storage[getIndex(uuid)];
    }

    protected void updateResume(Resume r) {
        storage[getIndex(r.getUuid())] = r;
    }
}