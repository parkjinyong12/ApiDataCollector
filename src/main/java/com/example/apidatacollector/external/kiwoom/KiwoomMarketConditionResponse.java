package com.example.apidatacollector.external.kiwoom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record KiwoomMarketConditionResponse(
        Header header,
        Body body
) {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Header(
            @JsonProperty("cont-yn") String continueYn,
            @JsonProperty("next-key") String nextKey,
            @JsonProperty("api-id") String apiId
    ) {
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Body(
            @JsonProperty("orgn_prsm_avg_pric") String institutionAveragePrice,
            @JsonProperty("for_prsm_avg_pric") String foreignerAveragePrice,
            @JsonProperty("stk_orgn_trde_trnsn") List<TradingTrend> tradingTrends
    ) {
        public Body {
            tradingTrends = tradingTrends == null ? List.of() : List.copyOf(tradingTrends);
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record TradingTrend(
            @JsonProperty("dt") String date,
            @JsonProperty("close_pric") String closingPrice,
            @JsonProperty("pre_sig") String comparisonSymbol,
            @JsonProperty("pred_pre") String differenceFromPreviousDay,
            @JsonProperty("flu_rt") String fluctuationRate,
            @JsonProperty("trde_qty") String tradeVolume,
            @JsonProperty("orgn_dt_acc") String institutionAccumulated,
            @JsonProperty("orgn_daly_nettrde_qty") String institutionDailyNetTradeQuantity,
            @JsonProperty("for_dt_acc") String foreignerAccumulated,
            @JsonProperty("for_daly_nettrde_qty") String foreignerDailyNetTradeQuantity,
            @JsonProperty("limit_exh_rt") String limitExhaustionRate
    ) {
    }
}
