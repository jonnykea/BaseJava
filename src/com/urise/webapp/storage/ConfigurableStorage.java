package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.List;

public class ConfigurableStorage implements Storage{
    private StorageStrategy storage;

    public ConfigurableStorage(StorageStrategy storage) {
        this.storage = storage;
    }

    public void setStorage(StorageStrategy storage) {
        this.storage = storage;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Resume r) {
        storage.save(r);
    }

    @Override
    public void update(Resume r) {
        storage.update(r);
    }

    @Override
    public Resume get(String uuid) {
        return storage.get(uuid);
    }

    @Override
    public void delete(String uuid) {
        storage.delete(uuid);
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage.getAllSorted();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
