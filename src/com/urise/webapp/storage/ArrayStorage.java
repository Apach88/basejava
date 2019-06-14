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
        if (isPresent(r.getUuid())) {
            System.out.println("ERROR: " + r.getUuid() + " is already exist");
        }
        else {
            if (size != storage.length) {
                storage[size] = r;
                size++;
            }
            else {
                System.out.println("ERROR: no space in storage");
            }
        }
    }

    public Resume get(String uuid) {
        if (isPresent(uuid)){
            for (Resume r : getAll()) {
                if (r.getUuid().equals(uuid)) {
                    return r;
                }
            }
        } else {
            System.out.println("ERROR: " + uuid + " is not present in storage");
        }
        return null;
    }

    public void delete(String uuid) {
        if (isPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    if (i != size - 1) {
                        storage[i] = storage[size - 1];
                        storage[size - 1] = null;
                    } else storage[i] = null;
                    size--;
                }
            }
        } else {
            System.out.println("ERROR: " + uuid + " is not exist in storage");
        }
    }

    public void update (Resume r) {
        if (isPresent(r.getUuid())) {
            Resume resume = get(r.getUuid());
            resume = r;
        }
        else {
            System.out.println("ERROR: " + r.getUuid() + " is not exist in storage");
        }
    }

    public boolean isPresent (String id) {
        for (Resume r : getAll()) {
            if (r.getUuid().equals(id)){
                return true;
            }
        }
        return false;
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
