package ru.jenya.data;

import org.jetbrains.annotations.NotNull;

public class ServerFile {

    @NotNull
    public final String name;
    @NotNull
    private final byte[] content;

    public ServerFile(@NotNull String name, @NotNull byte[] content) {
        this.name = name;
        this.content = content;
    }

    @NotNull
    public byte[] getContent() {
        return content;
    }
}
