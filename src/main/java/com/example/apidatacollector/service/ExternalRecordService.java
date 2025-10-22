package com.example.apidatacollector.service;

import com.example.apidatacollector.domain.ExternalRecord;
import com.example.apidatacollector.repository.ExternalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ExternalRecordService {

    private final ExternalApiClient externalApiClient;
    private final ExternalRecordRepository repository;

    public ExternalRecordService(ExternalApiClient externalApiClient, ExternalRecordRepository repository) {
        this.externalApiClient = externalApiClient;
        this.repository = repository;
    }

    @Transactional
    public ExternalRecord fetchAndStore(String source, Map<String, String> headers, Map<String, Object> body) {
        Map<String, String> safeHeaders = headers != null ? headers : Collections.emptyMap();
        Map<String, Object> safeBody = body != null ? body : Collections.emptyMap();

        ExternalApiResponse response = externalApiClient.fetchData(new ExternalApiRequest(safeHeaders, safeBody));

        String requestDetails = String.format("headers=%s, body=%s", safeHeaders, safeBody);

        ExternalRecord record = new ExternalRecord(
                source,
                response.getStatusCode(),
                response.getBody(),
                OffsetDateTime.now(),
                requestDetails
        );

        return repository.save(record);
    }

    @Transactional(readOnly = true)
    public List<ExternalRecord> findAll() {
        return repository.findAll();
    }
}
