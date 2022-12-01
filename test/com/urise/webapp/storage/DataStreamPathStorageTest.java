package com.urise.webapp.storage;

import com.urise.webapp.storage.strategy.DataStreamSerializer;

public class DataStreamPathStorageTest extends AbstractStorageTest {
    public DataStreamPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new DataStreamSerializer()));
    }
}