package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;


public class ListStorage extends AbstractStorage {
    List<Resume> listResume = new ArrayList<>();

    public final int size() {
        return listResume.size();
    }

    public final void clear() {
        listResume.clear();
    }

    public Resume[] getAll() {
        Resume[] array = new Resume[listResume.size()];
        return listResume.toArray(array);
    }

    private int getIndex(Resume r) {
        return listResume.indexOf(r);
    }

    protected final boolean isOverFlow() {
        return false;
    }

    protected final boolean contains(Resume r) {
        return listResume.contains(r);
    }

    protected final void store(Resume r) {
        listResume.add(r);
    }

    protected void deleteResume(String uuid) {
        Resume r = new Resume(uuid);
        listResume.remove(r);
    }

    protected Resume returnResume(String uuid) {
        return listResume.get(getIndex(new Resume(uuid)));
    }

    protected void updateResume(Resume r) {
        listResume.set(getIndex(r), r);
    }
}

