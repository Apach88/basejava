package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertValueToArray(int position, Resume resume) {
        int index = position - 1;
        if (storage[index] == null) {
            storage[index] = resume;
        } else {
            System.arraycopy(storage, index, storage, position, size - index);
            storage[index] = resume;
        }
    }

    @Override
    protected void deleteValueFromArray(int position) {
        System.arraycopy(storage, position + 1, storage, position, size - position);
    }
}
