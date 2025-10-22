package com.example.apidatacollector.service;

public interface ExternalApiClient {

    ExternalApiResponse fetchData(ExternalApiRequest request);
}
