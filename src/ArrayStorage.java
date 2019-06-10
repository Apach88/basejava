import java.util.Arrays;
import java.util.Comparator;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {

        for (int i = 0; i < size(); i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {

        storage[size()] = r;
    }

    Resume get(String uuid) {

        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid))
                return storage[i];
        }

        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = null;
            }
        }
        sort();
    }

    void sort() {
        Arrays.sort(storage, new Comparator<Resume>() {
            @Override
            public int compare(Resume o1, Resume o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                if (o1 == null) {
                    return 1;
                }
                if (o2 == null) {
                    return -1;
                }
                return o1.compareTo(o2);
            }
        });
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {

        return Arrays.copyOfRange(storage, 0, size());
    }

    int size() {
        int i;
        for (i = 0; i < storage.length; i++) {
            if (storage[i] == null)
                break;
        }
        return i;
    }
}
