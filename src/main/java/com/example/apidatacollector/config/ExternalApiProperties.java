package com.example.apidatacollector.config;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "external.api")
public class ExternalApiProperties {

    /**
     * Base URL of the external API (for example, https://api.example.com).
     */
    @NotBlank
    private String baseUrl;

    /**
     * Relative path that should be appended to the base URL when fetching data.
     */
    @NotBlank
    private String resourcePath;

    /**
     * Optional API key or token used for authenticating requests.
     */
    private String apiKey;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
