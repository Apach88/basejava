package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage,null);
        size = 0;
    }

    public void save(Resume r) {
        if (getIndexIfPresent(r.getUuid()) >= 0) {
            System.out.println("ERROR: " + r.getUuid() + " already exist");
        }
        else {
            if (size != storage.length) {
                storage[size] = r;
                size++;
            }
            else {
                System.out.println("ERROR: no space in the storage");
            }
        }
    }

    public Resume get(String uuid) {
        int resumeIndex = getIndexIfPresent(uuid);
        if (resumeIndex >= 0){
            return storage[resumeIndex];
        } else {
            System.out.println("ERROR: " + uuid + " does not exist in the storage");
        }
        return null;
    }

    public void delete(String uuid) {
        int resumeIndex = getIndexIfPresent(uuid);
        if (resumeIndex >= 0) {
            if (resumeIndex != size -1) {
                storage[resumeIndex] = storage[size - 1];
                storage[size - 1] = null;
            } else {
                storage[resumeIndex] = null;
            }
            size--;
        } else {
            System.out.println("ERROR: " + uuid + " does not exist in the storage");
        }
    }

    public void update (Resume r) {
        int resumeIndex = getIndexIfPresent(r.getUuid());
        if (resumeIndex >= 0) {
            storage[resumeIndex] = r;
        }
        else {
            System.out.println("ERROR: " + r.getUuid() + " does not exist in the storage");
        }
    }

    public int getIndexIfPresent (String uuid) {
        for (int i = 0; i < size; i++)
        {
            if (storage[i].getUuid().equals(uuid)){
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return this.size;
    }

}
