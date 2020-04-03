package ru.jenya;

import ru.jenya.config.Config;
import ru.jenya.http.URIManager;
import ru.jenya.storage.NewStorage;
import ru.jenya.storage.Storage;
import ru.jenya.storage.StorageException;
import ru.jenya.storage.StorageImpl;

public class Application {

    public static void main(String[] args) throws StorageException {
        URIManager uriManager = new URIManager(Config.instance.scheme, Config.instance.host, Config.instance.port);
        Storage oldStorage = new StorageImpl("oldStorage", uriManager);
        NewStorage newStorage = new NewStorage("newStorage", uriManager);
        DataManager manager = new DataManager();
        manager.migrate(oldStorage, newStorage);
    }
}

