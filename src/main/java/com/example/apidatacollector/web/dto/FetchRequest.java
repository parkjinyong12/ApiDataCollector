package com.example.apidatacollector.web.dto;

import com.example.apidatacollector.external.kiwoom.KiwoomMarketConditionRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FetchRequest {

    @NotBlank
    private String source;

    @Valid
    @NotNull
    private KiwoomMarketConditionRequest request;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public KiwoomMarketConditionRequest getRequest() {
        return request;
    }

    public void setRequest(KiwoomMarketConditionRequest request) {
        this.request = request;
    }
}
