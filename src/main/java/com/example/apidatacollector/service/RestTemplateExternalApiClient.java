package com.example.apidatacollector.service;

import com.example.apidatacollector.config.ExternalApiProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Component
public class RestTemplateExternalApiClient implements ExternalApiClient {

    private final RestTemplate restTemplate;
    private final ExternalApiProperties properties;

    public RestTemplateExternalApiClient(RestTemplate restTemplate, ExternalApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public ExternalApiResponse fetchData(ExternalApiRequest request) {
        Assert.notNull(request, "External API request must not be null");

        URI uri = buildUri();
        RequestEntity<Object> requestEntity = RequestEntity
                .post(uri)
                .headers(headers -> {
                    request.getHeaders().forEach(headers::set);
                    if (!headers.containsKey(HttpHeaders.AUTHORIZATION) && StringUtils.hasText(properties.getApiKey())) {
                        headers.set(HttpHeaders.AUTHORIZATION, properties.getApiKey());
                    }
                    if (!headers.containsKey(HttpHeaders.ACCEPT)) {
                        headers.set(HttpHeaders.ACCEPT, "application/json");
                    }
                    if (!headers.containsKey(HttpHeaders.CONTENT_TYPE)) {
                        headers.set(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
                    }
                })
                .body(request.getBody().isEmpty() ? Collections.emptyMap() : request.getBody());

        ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
        String body = response.getBody() != null ? response.getBody() : "";
        return new ExternalApiResponse(response.getStatusCode().value(), body);
    }

    private URI buildUri() {
        Assert.hasText(properties.getBaseUrl(), "External API base URL must be configured");
        Assert.hasText(properties.getResourcePath(), "External API resource path must be configured");

        return UriComponentsBuilder
                .fromHttpUrl(properties.getBaseUrl())
                .path(properties.getResourcePath())
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
    }
}
