package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage {
    List<Resume> listResume = new ArrayList<>();

    @Override
    public final int size() {
        return listResume.size();
    }

    @Override
    public final void clear() {
        listResume.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] array = new Resume[listResume.size()];
        return listResume.toArray(array);
    }

    private int getIndex(Resume r) {
        return listResume.indexOf(r);
    }

    @Override
    protected final boolean isOverFlow() {
        return false;
    }

    @Override
    protected final boolean contains(Resume r) {
        return listResume.contains(r);
    }

    @Override
    protected final void store(Resume r) {
        listResume.add(r);
    }

    @Override
    protected void deleteResume(String uuid) {
        listResume.remove(new Resume(uuid));
    }

    @Override
    protected Resume returnResume(Resume r) {
        return listResume.get(getIndex(r));
    }

    @Override
    protected void updateResume(Resume r) {
        listResume.set(getIndex(r), r);
    }
}

