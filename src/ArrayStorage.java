import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        for (int i = 0; i < getSize(); i++) {
            storage[i] = null;
        }
        setSize(0);
    }

    void save(Resume r) {
        int s = getSize();
        storage[s] = r;
        setSize(++s);
    }

    Resume get(String uuid) {
        for (int i = 0; i < getSize(); i++) {
            if (storage[i].getUuid().equals(uuid))
                return storage[i];
        }
        return null;
    }

    void delete(String uuid) {
        int s = getSize();
        for (int i = 0; i < s; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                if (i != s - 1) {
                    storage[i] = storage[s - 1];
                    storage[s - 1] = null;
                } else storage[i] = null;
                setSize(--s);
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, getSize());
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
