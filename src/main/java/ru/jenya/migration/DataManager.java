package ru.jenya.migration;

import org.jetbrains.annotations.NotNull;
import ru.jenya.data.ServerFile;
import ru.jenya.storage.NewStorage;
import ru.jenya.storage.Storage;
import ru.jenya.storage.StorageException;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class DataManager {


    public void migrate(@NotNull Storage oldStorage, @NotNull NewStorage newStorage) throws StorageException {
        String[] files = oldStorage.files();
        AtomicReference<StorageException> throwable = new AtomicReference<>(null);
        Arrays.stream(files).parallel().forEach(fileName -> {
            try {
                ServerFile file = oldStorage.get(fileName);
                if (!newStorage.put(file)) {
                    throwable.set(new StorageException(String.format("Can't upload %s", fileName)));
                } else {
                    oldStorage.delete(fileName);
                }
            } catch (StorageException e) {
                throwable.set(e);
            }
        });
        if (throwable.get() != null) {
            throw throwable.get();
        }
    }
}
