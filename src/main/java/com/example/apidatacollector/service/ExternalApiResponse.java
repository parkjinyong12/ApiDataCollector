package com.example.apidatacollector.service;

public class ExternalApiResponse {

    private final int statusCode;
    private final String body;

    public ExternalApiResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }
}
