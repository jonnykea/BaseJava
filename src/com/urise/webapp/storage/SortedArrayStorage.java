package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void deleteElement(int index){
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
        storage[size - 1] = null;
    }

    @Override
    protected void saveElement(Resume r) {
        int insertionPoint = (getIndex(r.getUuid()) + 1) * -1;
        System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
        storage[insertionPoint] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
