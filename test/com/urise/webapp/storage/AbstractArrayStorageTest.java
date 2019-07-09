package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume[] RESUMES = new Resume[]
            {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = AssertionError.class)
    public void update() throws Exception {
        Resume oldResume = storage.get(UUID_2);
        storage.update(new Resume(UUID_2));
        Assert.assertSame(oldResume, storage.get(UUID_2));
    }

    @Test
    public void getAll() throws Exception {
        Assert.assertEquals(storage.size(), storage.getAll().length);
        Assert.assertArrayEquals(RESUMES, storage.getAll());
    }

    @Test
    public void save() throws Exception {
        Resume resume = new Resume("uuid4");
        storage.save(resume);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(resume, storage.get("uuid4"));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        try {
            storage.get(UUID_1);
            Assert.fail("Resume has not been deleted");
        } catch (NotExistStorageException e){
        }
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(RESUMES[0], storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("uuid" + i + 1));
            }
        } catch (StorageException e) {
            Assert.fail("Storage will not be overflow");
        }
        storage.save(new Resume("overUuid"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist(){
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist(){
        storage.update(new Resume("uuid5"));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist(){
        storage.delete("uuid5");
    }
}