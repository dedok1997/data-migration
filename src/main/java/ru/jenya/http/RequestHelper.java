package ru.jenya.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jetbrains.annotations.NotNull;
import ru.jenya.config.Config;

public class RequestHelper {

    @NotNull
    public static CloseableHttpClient httpClient() {
        return HttpClients.custom().setServiceUnavailableRetryStrategy(new RetryStrategy(Config.instance.maxRetry)).build();
    }
}
