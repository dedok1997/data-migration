package ru.jenya.utils;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;

public class IOHelperTest {

    @Test
    public void readToArray() throws IOException {
        byte[] arr = new byte[]{1, 2, 3};
        HttpEntity entity = new ByteArrayEntity(arr);
        assertArrayEquals(arr, IOHelper.readToArray(entity));
    }
}