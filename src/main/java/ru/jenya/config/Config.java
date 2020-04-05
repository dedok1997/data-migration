package ru.jenya.config;

import java.io.IOException;
import java.util.Properties;

public final class Config {

    public static final Config instance = new Config();
    public final int port;
    public final int maxRetry;
    public final String scheme;
    public final String host;
    private final Properties properties = new Properties();

    private Config() {
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
            port = Integer.parseInt(properties.getProperty("port"));
            maxRetry = Integer.parseInt(properties.getProperty("max.retry"));
            scheme = properties.getProperty("scheme");
            host = properties.getProperty("host");
        } catch (IOException | NumberFormatException e) {
            throw new LoadConfigException("Can't load application.properties", e);
        }
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
