package ru.jenya.storage;

public class StorageException extends Exception {

    public StorageException(Throwable cause) {
        super(cause);
    }

    public StorageException(String message) {
        super(message);
    }
}
