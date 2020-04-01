package ru.jenya.storage;

import ru.jenya.data.ServerFile;

public interface Storage {

    String[] files() throws StorageException;

    ServerFile get(String name) throws StorageException;

    boolean put(ServerFile serverFile) throws StorageException;
}
