package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;


public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    public final int size() {
        return storage.size();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public final void clear() {
        storage.clear();
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected final boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected final void doSave(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.put(r.getUuid(), r);
    }
}