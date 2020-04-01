package ru.jenya.utils;

import org.apache.http.HttpEntity;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class IOHelper {

    private IOHelper() {

    }

    @NotNull
    public static byte[] readToArray(@NotNull HttpEntity entity) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        entity.writeTo(outputStream);
        return outputStream.toByteArray();
    }
}
