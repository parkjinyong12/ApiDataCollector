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
import java.util.Map;

@Component
public class RestTemplateExternalApiClient implements ExternalApiClient {

    private final RestTemplate restTemplate;
    private final ExternalApiProperties properties;

    public RestTemplateExternalApiClient(RestTemplate restTemplate, ExternalApiProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public ExternalApiResponse fetchData(Map<String, String> queryParameters) {
        Assert.notNull(queryParameters, "Query parameters must not be null");

        URI uri = buildUri(queryParameters);
        RequestEntity<Void> request = RequestEntity.get(uri)
                .headers(headers -> {
                    if (StringUtils.hasText(properties.getApiKey())) {
                        headers.add(HttpHeaders.AUTHORIZATION, properties.getApiKey());
                    }
                    headers.set(HttpHeaders.ACCEPT, "application/json");
                })
                .build();

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        String body = response.getBody() != null ? response.getBody() : "";
        return new ExternalApiResponse(response.getStatusCode().value(), body);
    }

    private URI buildUri(Map<String, String> queryParameters) {
        Assert.hasText(properties.getBaseUrl(), "External API base URL must be configured");
        Assert.hasText(properties.getResourcePath(), "External API resource path must be configured");

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(properties.getBaseUrl())
                .path(properties.getResourcePath());

        queryParameters.forEach(builder::queryParam);

        return builder
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
    }
}
