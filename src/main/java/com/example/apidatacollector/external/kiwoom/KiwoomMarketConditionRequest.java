package com.example.apidatacollector.external.kiwoom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record KiwoomMarketConditionRequest(
        @Valid @NotNull Header header,
        @Valid @NotNull Body body
) {

    public Map<String, String> httpHeaders() {
        Map<String, String> headers = new LinkedHashMap<>();
        if (header == null) {
            return headers;
        }
        if (header.authorization() != null) {
            headers.put("Authorization", header.authorization());
        }
        if (header.contYn() != null) {
            headers.put("cont-yn", header.contYn());
        }
        if (header.nextKey() != null) {
            headers.put("next-key", header.nextKey());
        }
        if (header.apiId() != null) {
            headers.put("api-id", header.apiId());
        }
        return headers;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Header(
            @JsonProperty("authorization") @NotBlank String authorization,
            @JsonProperty("cont-yn") String contYn,
            @JsonProperty("next-key") String nextKey,
            @JsonProperty("api-id") @NotBlank String apiId
    ) {
        public Header {
            authorization = Objects.requireNonNull(authorization, "authorization must not be null");
            apiId = Objects.requireNonNull(apiId, "apiId must not be null");
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("stk_cd") @NotBlank String stockCode,
            @JsonProperty("strt_dt") @NotBlank String startDate,
            @JsonProperty("end_dt") @NotBlank String endDate,
            @JsonProperty("orgn_prsm_unp_tp") @NotBlank String institutionPriceType,
            @JsonProperty("for_prsm_unp_tp") @NotBlank String foreignerPriceType
    ) {
        public Body {
            stockCode = Objects.requireNonNull(stockCode, "stockCode must not be null");
            startDate = Objects.requireNonNull(startDate, "startDate must not be null");
            endDate = Objects.requireNonNull(endDate, "endDate must not be null");
            institutionPriceType = Objects.requireNonNull(institutionPriceType, "institutionPriceType must not be null");
            foreignerPriceType = Objects.requireNonNull(foreignerPriceType, "foreignerPriceType must not be null");
        }
    }
}
