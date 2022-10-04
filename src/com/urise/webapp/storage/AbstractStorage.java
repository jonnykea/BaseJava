package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void save(Resume r) {
        getExistingSearchKey(r.getUuid());
        doSave(r);
    }

    @Override
    public void update(Resume r) {
        getNotExistingSearchKey(r.getUuid());
        doUpdate(r);
    }

    @Override
    public Resume get(String uuid) {
        getNotExistingSearchKey(uuid);
        return doGet(uuid);
    }

    @Override
    public void delete(String uuid) {
        getNotExistingSearchKey(uuid);
        doDelete(uuid);
    }

    protected void getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected void getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doSave(Resume r);

    protected abstract void doUpdate(Resume r);

    protected abstract Resume doGet(String uuid);

    protected abstract void doDelete(String uuid);
}
