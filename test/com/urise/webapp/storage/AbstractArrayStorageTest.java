package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

abstract class AbstractArrayStorageTest {
    private final Storage storage;

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";

    private final Resume TEST_OBJECT1 = new Resume(UUID_1);
    private final Resume TEST_OBJECT2 = new Resume(UUID_2);
    private final Resume TEST_OBJECT3 = new Resume(UUID_3);


    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume test1 = storage.get(TEST_OBJECT1.getUuid());
        storage.update(TEST_OBJECT1);
        Resume test2 = storage.get(TEST_OBJECT1.getUuid());
        assertEquals(test1, test2);
    }

    @Test
    public void save() {
        String test = "test";
        Resume resumeTest = new Resume(test);
        storage.save(resumeTest);
        Resume testResume = storage.get(test);
        assertEquals(testResume, resumeTest);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws StorageException {
        try {
            for (int i = 4; i <= AbstractArrayStorage.STORAGE_LIMIT; i++) {
                String test = "uuid" + i;
                storage.save(new Resume(test));
            }
        } catch (Exception e) {
            Assert.fail("Overflow occurred earlier than it was planed");
        }
        String test = "test";
        storage.save(new Resume(test));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test
    public void get() throws Exception {
        Resume resume = storage.get(UUID_3);
        assertEquals(TEST_OBJECT3, resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void getAll() throws Exception {
        Resume[] testResumes = storage.getAll();
        Assert.assertEquals(testResumes[0],TEST_OBJECT1);
        Assert.assertEquals(testResumes[1],TEST_OBJECT2);
        Assert.assertEquals(testResumes[2],TEST_OBJECT3);
    }
}