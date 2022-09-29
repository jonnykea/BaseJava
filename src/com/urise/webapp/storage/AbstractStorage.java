package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        if (isOverFlow()) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else if (contains(r)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            store(r);
        }
    }

    @Override
    public void update(Resume r) {
        if (!contains(r)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            updateResume(r);
        }
    }

    @Override
    public Resume get(String uuid) {
        Resume r = new Resume(uuid);
        if (!contains(r)) {
            throw new NotExistStorageException(r.getUuid());
        }
        return returnResume(uuid);
    }

    @Override
    public void delete(String uuid) {
        Resume r = new Resume(uuid);
        if (!contains(r)) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(uuid);
    }

    protected abstract boolean isOverFlow();

    protected abstract boolean contains(Resume r);

    protected abstract void store(Resume r);

    protected abstract void deleteResume(String uuid);

    protected abstract Resume returnResume(String uuid);

    protected abstract void updateResume(Resume r);
}
