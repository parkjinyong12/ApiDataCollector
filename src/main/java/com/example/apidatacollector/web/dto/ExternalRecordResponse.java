package com.example.apidatacollector.web.dto;

import com.example.apidatacollector.domain.ExternalRecord;

import java.time.OffsetDateTime;

public class ExternalRecordResponse {

    private final Long id;
    private final String source;
    private final Integer statusCode;
    private final String payload;
    private final OffsetDateTime retrievedAt;
    private final String requestParameters;

    public ExternalRecordResponse(ExternalRecord record) {
        this.id = record.getId();
        this.source = record.getSource();
        this.statusCode = record.getStatusCode();
        this.payload = record.getPayload();
        this.retrievedAt = record.getRetrievedAt();
        this.requestParameters = record.getRequestParameters();
    }

    public Long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getPayload() {
        return payload;
    }

    public OffsetDateTime getRetrievedAt() {
        return retrievedAt;
    }

    public String getRequestParameters() {
        return requestParameters;
    }
}
