package ru.jenya.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
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
import ru.jenya.utils.IOHelper;

import java.io.IOException;
import java.net.URISyntaxException;

public class StorageImpl implements Storage {


    private static final ObjectMapper objectMapper = new ObjectMapper();
    @NotNull
    protected final URIManager uriManager;
    @NotNull
    protected final String name;

    public StorageImpl(@NotNull String storageName, @NotNull URIManager uriManager) {
        name = storageName;
        this.uriManager = uriManager;
    }

    @Override
    public String[] files() throws StorageException {
        try (CloseableHttpClient client = RequestHelper.httpClient()) {
            HttpGet get = new HttpGet(uriManager.get(name, "/files"));
            HttpResponse response = client.execute(get);
            return objectMapper.readValue(response.getEntity().getContent(), String[].class);
        } catch (IOException | URISyntaxException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public ServerFile get(String name) throws StorageException {
        try (CloseableHttpClient client = RequestHelper.httpClient()) {
            HttpGet get = new HttpGet(uriManager.get(this.name, "/files/" + name));
            HttpResponse response = client.execute(get);
            return new ServerFile(name, IOHelper.readToArray(response.getEntity()));
        } catch (URISyntaxException | IOException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public boolean put(ServerFile data) throws StorageException {
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
