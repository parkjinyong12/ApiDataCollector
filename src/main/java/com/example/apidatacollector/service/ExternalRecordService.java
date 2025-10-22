package com.example.apidatacollector.service;

import com.example.apidatacollector.domain.ExternalRecord;
import com.example.apidatacollector.repository.ExternalRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
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
    public ExternalRecord fetchAndStore(String source, Map<String, String> queryParameters) {
        ExternalApiResponse response = externalApiClient.fetchData(queryParameters);

        ExternalRecord record = new ExternalRecord(
                source,
                response.getStatusCode(),
                response.getBody(),
                OffsetDateTime.now(),
                queryParameters.toString()
        );

        return repository.save(record);
    }

    @Transactional(readOnly = true)
    public List<ExternalRecord> findAll() {
        return repository.findAll();
    }
}
