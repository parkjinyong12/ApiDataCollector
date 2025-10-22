package com.example.apidatacollector.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.time.OffsetDateTime;

@Entity
@Table(name = "external_records")
public class ExternalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private Integer statusCode;

    @Lob
    @Column(nullable = false)
    private String payload;

    @Column(nullable = false)
    private OffsetDateTime retrievedAt;

    @Column
    private String requestParameters;

    protected ExternalRecord() {
        // JPA only
    }

    public ExternalRecord(String source, Integer statusCode, String payload, OffsetDateTime retrievedAt, String requestParameters) {
        this.source = source;
        this.statusCode = statusCode;
        this.payload = payload;
        this.retrievedAt = retrievedAt;
        this.requestParameters = requestParameters;
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
