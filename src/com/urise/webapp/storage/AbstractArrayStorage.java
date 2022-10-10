package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

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

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected final void doSave(Resume r, Object searchKey) {
        if (isOverFlow()) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            insertResume(r, (int) searchKey);
            size++;
        }
    }

    protected void doDelete(Object searchKey) {
        deleteResume((int) searchKey);
        storage[size - 1] = null;
        size--;
    }

    protected Resume doGet(Object searchKey) {
        return storage[(int) searchKey];
    }

    protected void doUpdate(Resume r, Object searchKey) {
        storage[(int) searchKey] = r;
    }

    protected abstract void deleteResume(int index);

    protected abstract void insertResume(Resume r, int index);

    protected final boolean isOverFlow() {
        return size == STORAGE_LIMIT;
    }
}