package ru.jenya;

import org.junit.Test;
import ru.jenya.storage.OldStorage;
import ru.jenya.storage.Storage;
import ru.jenya.storage.StorageException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class DataManagerTest {

    @Test
    public void migrate() throws StorageException {
        DataManager dataManager = new DataManager();
        OldStorage oldStorage = OldStorageMock.getRandomDataStorage(1000);
        Storage newStorage = OldStorageMock.getRandomDataStorage(0);
        Set<String> oldStorageFiles = new HashSet<>(Arrays.asList(oldStorage.files()));
        dataManager.migrate(oldStorage, newStorage);
        assertEquals(0, oldStorage.files().length);
        assertEquals(oldStorageFiles, new HashSet<>(Arrays.asList(newStorage.files())));
        ;
    }
}