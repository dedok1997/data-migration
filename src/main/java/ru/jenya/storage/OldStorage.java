package ru.jenya.storage;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jetbrains.annotations.NotNull;
import ru.jenya.http.RequestHelper;
import ru.jenya.http.URIManager;

import java.io.IOException;
import java.net.URISyntaxException;

public class OldStorage extends StorageImpl {

    public OldStorage(String storageName, URIManager uriManager) {
        super(storageName, uriManager);
    }

    public boolean delete(@NotNull String fileName) throws StorageException {
        try (CloseableHttpClient client = RequestHelper.httpClient()) {
            HttpDelete delete = new HttpDelete(uriManager.get(this.name, "/files/" + fileName));
            HttpResponse response = client.execute(delete);
            return response.getStatusLine().getStatusCode() == 200;
        } catch (IOException | URISyntaxException e) {
            throw new StorageException(e);
        }
    }
}
