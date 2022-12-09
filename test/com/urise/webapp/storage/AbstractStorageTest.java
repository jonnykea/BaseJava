package com.urise.webapp.storage;

import com.urise.webapp.ResumeTestData;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("C:\\Users\\Ulya\\IdeaProjects\\basejava\\storage");
    protected final Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    private static final String UUID_0 = "uuid0";
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final String UUID_NOT_EXIST = "dummy";

 /*   final Resume RESUME_1 = new Resume(UUID_1, "Jon");
    final Resume RESUME_2 = new Resume(UUID_2, "Max");
    final Resume RESUME_3 = new Resume(UUID_3, "Anna");
    final Resume RESUME_4 = new Resume(UUID_4, "Andrew");*/

    final Resume RESUME_1 = ResumeTestData.fillAllResumeFields(UUID_0, "Max");
    final Resume RESUME_2 = ResumeTestData.fillAllResumeFields(UUID_1, "Jon");
    final Resume RESUME_3 = ResumeTestData.fillAllResumeFields(UUID_2, "Anna");
    final Resume RESUME_4 = ResumeTestData.fillAllResumeFields(UUID_3, "Andrew");


    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void update() throws Exception {
        Resume test = new Resume(UUID_1, "Jon");
        storage.update(test);
        Resume updateResume = storage.get(test.getUuid());
        assertEquals(updateResume, test);

    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume test = new Resume(UUID_NOT_EXIST);
        storage.update(test);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertGet(RESUME_4);
        assertSize(4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_2);
        assertSize(2);
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(UUID_NOT_EXIST);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void getAll() throws Exception {
        List<Resume> test = storage.getAllSorted();
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        Collections.sort(expected);
        assertSize(test.size());
        assertEquals(test, expected);
    }

    public void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    public void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }
}