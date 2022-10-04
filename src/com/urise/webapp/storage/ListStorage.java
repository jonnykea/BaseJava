package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage {
    private final List<Resume> listResume = new ArrayList<>();

    @Override
    public final int size() {
        return listResume.size();
    }

    @Override
    public final void clear() {
        listResume.clear();
    }

    public List<Resume> getAll() {
        return new ArrayList<>(listResume);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < listResume.size(); i++) {
            if (listResume.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected final boolean isExist(Object index) {
        return index != null;
    }

    @Override
    protected final void doSave(Resume r, Object searchKey) {
        listResume.add(r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        listResume.remove((int) searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return listResume.get((int) searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        listResume.set((int) searchKey, r);
    }
}