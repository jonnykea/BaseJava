package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.strategy.StreamSerializerStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class FileStorage extends AbstractStorage<File> {
    private final File directory;

    private StreamSerializerStrategy streamSerializerStrategy;

    protected FileStorage(File directory, StreamSerializerStrategy streamSerializerStrategy) {
        requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writeable");
        }
        this.directory = directory;

        this.streamSerializerStrategy = streamSerializerStrategy;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected void doSave(Resume r, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        doUpdate(r, file);
    }

    @Override
    protected void doUpdate(Resume r, File file) {
        try {
            streamSerializerStrategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return streamSerializerStrategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("file not deleted ", file.getName());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        File[] files = createListFiles();
        List<Resume> list = new ArrayList<>(files.length);
        for (File f : files) {
            list.add(doGet(f));
        }
        return list;
    }

    @Override
    public void clear() {
        File[] files = createListFiles();
        for (File f : files) {
            doDelete(f);
        }
    }

    @Override
    public int size() {
        File[] files = createListFiles();
        return files.length;
    }

    File[] createListFiles(){
        File[] files = directory.listFiles();
        if (files == null) {
            throw new StorageException("IO error ", directory.getName());
        }
        return files;
    }
}