package com.example.apidatacollector.service;

import com.example.apidatacollector.domain.ExternalRecord;
import com.example.apidatacollector.external.kiwoom.KiwoomMarketConditionRequest;
import com.example.apidatacollector.external.kiwoom.KiwoomMarketConditionResponse;
import com.example.apidatacollector.repository.ExternalRecordRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class ExternalRecordService {

    private final ExternalApiClient externalApiClient;
    private final ExternalRecordRepository repository;
    private final ObjectMapper objectMapper;

    public ExternalRecordService(ExternalApiClient externalApiClient, ExternalRecordRepository repository, ObjectMapper objectMapper) {
        this.externalApiClient = externalApiClient;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public ExternalRecord fetchAndStore(String source, KiwoomMarketConditionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Kiwoom request must not be null");
        }
        ExternalApiRequest externalRequest = new ExternalApiRequest(request.httpHeaders(), request.body());

        ExternalApiResponse response = externalApiClient.fetchData(externalRequest);

        KiwoomMarketConditionResponse parsedResponse = deserializeResponse(response.getBody());

        String serializedRequest = serializeSafely(request);
        String serializedResponse = serializeSafely(parsedResponse);

        ExternalRecord record = new ExternalRecord(
                source,
                response.getStatusCode(),
                serializedResponse,
                OffsetDateTime.now(),
                serializedRequest
        );

        return repository.save(record);
    }

    @Transactional(readOnly = true)
    public List<ExternalRecord> findAll() {
        return repository.findAll();
    }

    private KiwoomMarketConditionResponse deserializeResponse(String body) {
        if (body == null || body.isBlank()) {
            return new KiwoomMarketConditionResponse(null, null);
        }
        try {
            return objectMapper.readValue(body, KiwoomMarketConditionResponse.class);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException("Failed to parse Kiwoom response payload", ex);
        }
    }

    private String serializeSafely(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException ex) {
            throw new IllegalStateException("Failed to serialize payload", ex);
        }
    }
}
