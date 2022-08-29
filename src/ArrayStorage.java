import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int NUM_OF_RESUMES = 1000;
    private int resumeCount;
    Resume[] storage = new Resume[NUM_OF_RESUMES];

    public ArrayStorage() {
        storage[resumeCount++] = new Resume("uuid1");
        storage[resumeCount++] = new Resume("uuid2");
        storage[resumeCount++] = new Resume("uuid3");
    }

    void clear() {
        for (int i = 0; i < storage.length; i++) {
            storage[i] = null;
            resumeCount = 0;
        }
    }

    void save(Resume r) {
        storage[resumeCount++] = r;
    }

    Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        return null;
    }

    void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new IllegalArgumentException("Resume isn't found");
        }
        resumeCount--;
        System.arraycopy(storage, index + 1, storage, index, resumeCount - index);
        storage[resumeCount] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, resumeCount);
    }

    int size() {
        return resumeCount;
    }

    public boolean isFull() {
        return resumeCount >= 1000;
    }

    private int findIndex(String resume) {
        for (int i = 0; i < resumeCount; i++) {
            if (resume.equals(storage[i].uuid.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
}