package ru.jenya;

import ru.jenya.config.Config;
import ru.jenya.http.URIManager;
import ru.jenya.storage.OldStorage;
import ru.jenya.storage.Storage;
import ru.jenya.storage.StorageException;
import ru.jenya.storage.StorageImpl;

public class Application {

    public static void main(String[] args) throws StorageException {
        URIManager uriManager = new URIManager(Config.instance.scheme, Config.instance.host, Config.instance.port);
        OldStorage oldStorage = new OldStorage("oldStorage", uriManager);
        Storage newStorage = new StorageImpl("newStorage", uriManager);
        System.out.println(newStorage.files().length);
        System.out.println(oldStorage.files().length);
        DataManager manager = new DataManager();
        manager.migrate(oldStorage, newStorage);
    }
}

