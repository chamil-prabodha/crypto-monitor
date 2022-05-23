package com.zooplus.cryptomonitor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class TestUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private TestUtil() {}

    public static InputStream getResource(String path, Class<?> clazz) {
        return clazz.getResourceAsStream(path);
    }

    public static JsonNode getJsonNode(String path, Class<?> clazz) throws IOException {
        InputStream inputStream = getResource(path, clazz);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String file = bufferedReader.lines().collect(Collectors.joining("\n"));
        return objectMapper.readValue(file, JsonNode.class);
    }
}
