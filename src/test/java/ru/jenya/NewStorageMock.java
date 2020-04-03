package ru.jenya;

import org.jetbrains.annotations.NotNull;
import ru.jenya.data.ServerFile;
import ru.jenya.http.URIManager;
import ru.jenya.storage.NewStorage;
import ru.jenya.storage.StorageException;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class NewStorageMock extends NewStorage {

    private final static Random random = new Random();
    private final ConcurrentHashMap<String, ServerFile> data;

    public NewStorageMock(String storageName, URIManager uriManager) {
        super(storageName, uriManager);
        data = new ConcurrentHashMap<>();
    }

    public static NewStorage getRandomDataStorage(int length) throws StorageException {
        NewStorage storage = new NewStorageMock("", new URIManager("", "", 0));
        for (int i = 0; i < length; i++) {
            storage.put(new ServerFile(Integer.toString(i), Integer.toString(i).getBytes()));
        }
        return storage;
    }

    @Override
    public boolean delete(@NotNull String fileName) throws StorageException {
        if (!data.containsKey(fileName)) return false;
        data.remove(fileName);
        return true;
    }

    @Override
    public String[] files() throws StorageException {
        return data.keySet().toArray(new String[0]);
    }

    @Override
    public ServerFile get(@NotNull String name) throws StorageException {
        if (data.containsKey(name))
            return data.get(name);
        throw new StorageException("");
    }

    @Override
    public boolean put(@NotNull ServerFile file) throws StorageException {
        data.put(file.name, file);
        return true;
    }
}
