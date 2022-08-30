import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final static int NUM_OF_RESUMES = 1000;
    private int countResumes;
    Resume[] storage = new Resume[NUM_OF_RESUMES];

    void clear() {
            countResumes = 0;
    }

    void save(Resume r) {
        storage[countResumes++] = r;
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
        countResumes--;
        System.arraycopy(storage, index + 1, storage, index, countResumes - index);
        storage[countResumes] = null;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, countResumes);
    }

    int size() {
        return countResumes;
    }

    private int findIndex(String resume) {
        for (int i = 0; i < countResumes; i++) {
            if (resume.equals(storage[i].uuid.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
}