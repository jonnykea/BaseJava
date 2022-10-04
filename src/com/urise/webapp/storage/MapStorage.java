package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;


public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> MapResume = new TreeMap<>();

    @Override
    public final int size() {
        return MapResume.size();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList<>(MapResume.values());
    }

    @Override
    public final void clear() {
        MapResume.clear();
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return MapResume.get(uuid);
    }

    @Override
    protected final boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected final void doSave(Resume r) {
        MapResume.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(String uuid) {
        MapResume.remove(uuid);
    }

    @Override
    protected Resume doGet(String uuid) {
        return MapResume.get(uuid);
    }

    @Override
    protected void doUpdate(Resume r) {
        MapResume.put(r.getUuid(), r);
    }
}

