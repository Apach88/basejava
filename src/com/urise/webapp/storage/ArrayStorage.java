package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume newResume) {
        if (getIndexIfPresent(newResume.getUuid()) >= 0) {
            System.out.println("ERROR: " + newResume.getUuid() + " already exist");
        } else {
            if (size != storage.length) {
                storage[size] = newResume;
                size++;
            } else {
                System.out.println("ERROR: no space in the storage");
            }
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = getIndexIfPresent(uuid);
        if (resumeIndex >= 0) {
            return storage[resumeIndex];
        } else {
            System.out.println("ERROR: " + uuid + " does not exist in the storage");
            return null;
        }
    }

    public void delete(String uuid) {
        int resumeIndex = getIndexIfPresent(uuid);
        if (resumeIndex >= 0) {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("ERROR: " + uuid + " does not exist in the storage");
        }
    }

    public void update(Resume resumeToUpdate) {
        int resumeIndex = getIndexIfPresent(resumeToUpdate.getUuid());
        if (resumeIndex >= 0) {
            storage[resumeIndex] = resumeToUpdate;
        } else {
            System.out.println("ERROR: " + resumeToUpdate.getUuid() + " does not exist in the storage");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getIndexIfPresent(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
