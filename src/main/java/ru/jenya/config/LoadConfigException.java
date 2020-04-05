package ru.jenya.config;

public class LoadConfigException extends RuntimeException {
    public LoadConfigException(String message) {
        super(message);
    }

    public LoadConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
