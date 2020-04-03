package ru.jenya.http;


import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URISyntaxException;

public class URIManager {

    @NotNull
    private final String host;
    @NotNull
    private final String scheme;
    private final int port;

    public URIManager(@NotNull String scheme, @NotNull String host, int port) {
        this.scheme = scheme;
        this.host = host;
        this.port = port;
    }

    public URI get(@NotNull String name, @NotNull String path) throws URISyntaxException {
        return new URI(scheme, null, host, port, String.join("", "/", name, path), null, null);
    }
}
