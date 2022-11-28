package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.strategy.StreamSerializerStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class PathStorage extends AbstractStorage<Path> {
    private final Path directory;

    private StreamSerializerStrategy streamSerializerStrategy;

    protected PathStorage(String dir, StreamSerializerStrategy streamSerializerStrategy) {
        directory = Paths.get(dir);
        requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not readable/writeable");
        }
        this.streamSerializerStrategy = streamSerializerStrategy;
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    protected void doSave(Resume r, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create path " + path.toAbsolutePath(), path.getFileName().toString(), e);
        }
        doUpdate(r, path);
    }

    @Override
    protected void doUpdate(Resume r, Path path) {
        try {
            streamSerializerStrategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(path.toString())));
        } catch (IOException e) {
            throw new StorageException("Path write error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return streamSerializerStrategy.doRead(new BufferedInputStream(new FileInputStream(path.toString())));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.getFileName().toString(), e);
        }
    }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path not deleted ", path.getFileName().toString());
        }
    }

    @Override
    protected List<Resume> doCopyAll() {
        List<Resume> list;
        try {
            List<Path> paths = Files.list(directory).toList();
            list = new ArrayList<>(size());
            for (Path p : paths) {
                list.add(doGet(p));
            }
        } catch (IOException e) {
            throw new StorageException("IO error ", directory.getFileName().toString());
        }
        return list;
    }

    @Override
    public void clear() {
        createStreamList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) createStreamList().count();
    }

    Stream<Path> createStreamList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw new StorageException("Path access error", e);
        }
    }
}