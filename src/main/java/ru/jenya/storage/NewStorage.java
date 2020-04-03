package ru.jenya.storage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.jetbrains.annotations.NotNull;
import ru.jenya.data.ServerFile;
import ru.jenya.http.RequestHelper;
import ru.jenya.http.URIManager;

import java.io.IOException;
import java.net.URISyntaxException;

public class NewStorage extends StorageImpl {

    public NewStorage(String storageName, URIManager uriManager) {
        super(storageName, uriManager);
    }

    public boolean put(@NotNull ServerFile data) throws StorageException {
        try (CloseableHttpClient client = RequestHelper.httpClient()) {
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            entityBuilder.addPart("file", new ByteArrayBody(data.getContent(), data.name));
            HttpEntity entity = entityBuilder.build();
            RequestBuilder builder = RequestBuilder.post(uriManager.get(name, "/files"));
            builder.setEntity(entity);
            HttpUriRequest multipartRequest = builder.build();
            HttpResponse response = client.execute(multipartRequest);
            return response.getStatusLine().getStatusCode() == 200;
        } catch (URISyntaxException | IOException e) {
            throw new StorageException(e);
        }
    }

}
