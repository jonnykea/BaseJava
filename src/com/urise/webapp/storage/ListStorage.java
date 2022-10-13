package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> listResume = new ArrayList<>();

    @Override
    public final int size() {
        return listResume.size();
    }

    @Override
    public final void clear() {
        listResume.clear();
    }

    public List<Resume> doCopyAll() {
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
    protected final boolean isExist(Integer index) {
        return index != null;
    }

    @Override
    protected final void doSave(Resume r, Integer searchKey) {
        listResume.add(r);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        listResume.remove(searchKey.intValue());
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return listResume.get(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Integer searchKey) {
        listResume.set((int) searchKey, r);
    }
}