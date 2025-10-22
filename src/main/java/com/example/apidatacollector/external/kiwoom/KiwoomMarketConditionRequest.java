package com.example.apidatacollector.external.kiwoom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Data model that represents the request payload for the Kiwoom market condition API (TR ka10045).
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KiwoomMarketConditionRequest {

    @Valid
    @NotNull
    private Header header;

    @Valid
    @NotNull
    private Body body;

    public KiwoomMarketConditionRequest() {
        // default constructor for JSON binding
    }

    public KiwoomMarketConditionRequest(Header header, Body body) {
        this.header = header;
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    /**
     * Converts the header values into a map that can be applied to HTTP requests.
     */
    public Map<String, String> toHeaderMap() {
        Map<String, String> headers = new LinkedHashMap<>();
        if (header == null) {
            return headers;
        }
        if (header.getAuthorization() != null) {
            headers.put("Authorization", header.getAuthorization());
        }
        if (header.getContYn() != null) {
            headers.put("cont-yn", header.getContYn());
        }
        if (header.getNextKey() != null) {
            headers.put("next-key", header.getNextKey());
        }
        if (header.getApiId() != null) {
            headers.put("api-id", header.getApiId());
        }
        return headers;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Header {

        @JsonProperty("authorization")
        @NotBlank
        private String authorization;

        @JsonProperty("cont-yn")
        private String contYn;

        @JsonProperty("next-key")
        private String nextKey;

        @JsonProperty("api-id")
        @NotBlank
        private String apiId;

        public Header() {
        }

        public Header(String authorization, String contYn, String nextKey, String apiId) {
            this.authorization = authorization;
            this.contYn = contYn;
            this.nextKey = nextKey;
            this.apiId = apiId;
        }

        public String getAuthorization() {
            return authorization;
        }

        public void setAuthorization(String authorization) {
            this.authorization = authorization;
        }

        public String getContYn() {
            return contYn;
        }

        public void setContYn(String contYn) {
            this.contYn = contYn;
        }

        public String getNextKey() {
            return nextKey;
        }

        public void setNextKey(String nextKey) {
            this.nextKey = nextKey;
        }

        public String getApiId() {
            return apiId;
        }

        public void setApiId(String apiId) {
            this.apiId = apiId;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Body {

        @JsonProperty("stk_cd")
        @NotBlank
        private String stockCode;

        @JsonProperty("strt_dt")
        @NotBlank
        private String startDate;

        @JsonProperty("end_dt")
        @NotBlank
        private String endDate;

        @JsonProperty("orgn_prsm_unp_tp")
        @NotBlank
        private String institutionPriceType;

        @JsonProperty("for_prsm_unp_tp")
        @NotBlank
        private String foreignerPriceType;

        public Body() {
        }

        public Body(String stockCode, String startDate, String endDate, String institutionPriceType, String foreignerPriceType) {
            this.stockCode = stockCode;
            this.startDate = startDate;
            this.endDate = endDate;
            this.institutionPriceType = institutionPriceType;
            this.foreignerPriceType = foreignerPriceType;
        }

        public String getStockCode() {
            return stockCode;
        }

        public void setStockCode(String stockCode) {
            this.stockCode = stockCode;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getInstitutionPriceType() {
            return institutionPriceType;
        }

        public void setInstitutionPriceType(String institutionPriceType) {
            this.institutionPriceType = institutionPriceType;
        }

        public String getForeignerPriceType() {
            return foreignerPriceType;
        }

        public void setForeignerPriceType(String foreignerPriceType) {
            this.foreignerPriceType = foreignerPriceType;
        }
    }
}
