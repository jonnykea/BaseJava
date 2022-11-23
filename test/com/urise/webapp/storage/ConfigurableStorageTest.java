package com.urise.webapp.storage;

public class ConfigurableStorageTest extends AbstractStorageTest{
    public ConfigurableStorageTest() {
        super(new ConfigurableStorage(new ObjectStreamFileStorage(STORAGE_DIR)));
    }
}



