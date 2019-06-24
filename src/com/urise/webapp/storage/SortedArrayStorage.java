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
        int index = -position - 1;
        if (index == size) {
            storage[index] = resume;
        } else {
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = resume;
        }
    }

    @Override
    protected void deleteValueFromArray(int position) {
        int length = size - (position + 1);
        int startIndex = position + 1;

        System.arraycopy(getAll(), startIndex, storage, position, length);
    }
}
