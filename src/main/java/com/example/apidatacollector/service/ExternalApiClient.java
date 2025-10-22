package com.example.apidatacollector.service;

import java.util.Map;

public interface ExternalApiClient {

    ExternalApiResponse fetchData(Map<String, String> queryParameters);
}
