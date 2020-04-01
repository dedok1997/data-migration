package ru.jenya.config;

import java.io.IOException;
import java.util.Properties;

public final class Config {

    public final static Config instance = new Config();
    public final int port;
    public final int maxRetry;
    public final String scheme;
    public final String host;

    {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Can't load application.properties");
        }
        port = Integer.parseInt(properties.getProperty("port"));
        maxRetry = Integer.parseInt(properties.getProperty("max.retry"));
        scheme = properties.getProperty("scheme");
        host = properties.getProperty("host");

    }


    private Config() {
    }
}
