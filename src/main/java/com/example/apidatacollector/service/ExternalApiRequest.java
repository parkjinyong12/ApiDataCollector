package com.example.apidatacollector.service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExternalApiRequest {

    private final Map<String, String> headers;
    private final Map<String, Object> body;

    public ExternalApiRequest(Map<String, String> headers, Map<String, Object> body) {
        this.headers = toUnmodifiableMap(headers);
        this.body = toUnmodifiableMap(body);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    private static <K, V> Map<K, V> toUnmodifiableMap(Map<K, V> source) {
        if (source == null || source.isEmpty()) {
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(new LinkedHashMap<>(source));
    }
}
