package ru.jenya.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.protocol.HttpContext;
import org.jetbrains.annotations.NotNull;

public class RetryStrategy implements ServiceUnavailableRetryStrategy {

    private final int maxRetry;

    public RetryStrategy(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    @Override
    public boolean retryRequest(@NotNull HttpResponse response, int executionCount, @NotNull HttpContext context) {
        return response.getStatusLine().getStatusCode() == 500 &&
                executionCount < maxRetry;
    }

    @Override
    public long getRetryInterval() {
        return 0;
    }
}