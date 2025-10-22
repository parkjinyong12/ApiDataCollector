package com.example.apidatacollector.external.kiwoom;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Data model for the Kiwoom market condition API response. The API delivers a header section containing
 * pagination metadata and a body section containing the actual trading trend data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KiwoomMarketConditionResponse {

    private Header header;

    private Body body;

    public KiwoomMarketConditionResponse() {
    }

    public KiwoomMarketConditionResponse(Header header, Body body) {
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Header {

        @JsonProperty("cont-yn")
        private String continueYn;

        @JsonProperty("next-key")
        private String nextKey;

        @JsonProperty("api-id")
        private String apiId;

        public Header() {
        }

        public Header(String continueYn, String nextKey, String apiId) {
            this.continueYn = continueYn;
            this.nextKey = nextKey;
            this.apiId = apiId;
        }

        public String getContinueYn() {
            return continueYn;
        }

        public void setContinueYn(String continueYn) {
            this.continueYn = continueYn;
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

        @JsonProperty("orgn_prsm_avg_pric")
        private String institutionAveragePrice;

        @JsonProperty("for_prsm_avg_pric")
        private String foreignerAveragePrice;

        @JsonProperty("stk_orgn_trde_trnsn")
        private List<TradingTrend> tradingTrends = new ArrayList<>();

        public Body() {
        }

        public Body(String institutionAveragePrice, String foreignerAveragePrice, List<TradingTrend> tradingTrends) {
            this.institutionAveragePrice = institutionAveragePrice;
            this.foreignerAveragePrice = foreignerAveragePrice;
            if (tradingTrends != null) {
                this.tradingTrends = tradingTrends;
            }
        }

        public String getInstitutionAveragePrice() {
            return institutionAveragePrice;
        }

        public void setInstitutionAveragePrice(String institutionAveragePrice) {
            this.institutionAveragePrice = institutionAveragePrice;
        }

        public String getForeignerAveragePrice() {
            return foreignerAveragePrice;
        }

        public void setForeignerAveragePrice(String foreignerAveragePrice) {
            this.foreignerAveragePrice = foreignerAveragePrice;
        }

        public List<TradingTrend> getTradingTrends() {
            return tradingTrends;
        }

        public void setTradingTrends(List<TradingTrend> tradingTrends) {
            this.tradingTrends = tradingTrends;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TradingTrend {

        @JsonProperty("dt")
        private String date;

        @JsonProperty("close_pric")
        private String closingPrice;

        @JsonProperty("pre_sig")
        private String comparisonSymbol;

        @JsonProperty("pred_pre")
        private String differenceFromPreviousDay;

        @JsonProperty("flu_rt")
        private String fluctuationRate;

        @JsonProperty("trde_qty")
        private String tradeVolume;

        @JsonProperty("orgn_dt_acc")
        private String institutionAccumulated;

        @JsonProperty("orgn_daly_nettrde_qty")
        private String institutionDailyNetTradeVolume;

        @JsonProperty("for_dt_acc")
        private String foreignerAccumulated;

        @JsonProperty("for_daly_nettrde_qty")
        private String foreignerDailyNetTradeVolume;

        @JsonProperty("limit_exh_rt")
        private String limitExhaustionRate;

        public TradingTrend() {
        }

        public TradingTrend(String date, String closingPrice, String comparisonSymbol, String differenceFromPreviousDay,
                            String fluctuationRate, String tradeVolume, String institutionAccumulated,
                            String institutionDailyNetTradeVolume, String foreignerAccumulated,
                            String foreignerDailyNetTradeVolume, String limitExhaustionRate) {
            this.date = date;
            this.closingPrice = closingPrice;
            this.comparisonSymbol = comparisonSymbol;
            this.differenceFromPreviousDay = differenceFromPreviousDay;
            this.fluctuationRate = fluctuationRate;
            this.tradeVolume = tradeVolume;
            this.institutionAccumulated = institutionAccumulated;
            this.institutionDailyNetTradeVolume = institutionDailyNetTradeVolume;
            this.foreignerAccumulated = foreignerAccumulated;
            this.foreignerDailyNetTradeVolume = foreignerDailyNetTradeVolume;
            this.limitExhaustionRate = limitExhaustionRate;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getClosingPrice() {
            return closingPrice;
        }

        public void setClosingPrice(String closingPrice) {
            this.closingPrice = closingPrice;
        }

        public String getComparisonSymbol() {
            return comparisonSymbol;
        }

        public void setComparisonSymbol(String comparisonSymbol) {
            this.comparisonSymbol = comparisonSymbol;
        }

        public String getDifferenceFromPreviousDay() {
            return differenceFromPreviousDay;
        }

        public void setDifferenceFromPreviousDay(String differenceFromPreviousDay) {
            this.differenceFromPreviousDay = differenceFromPreviousDay;
        }

        public String getFluctuationRate() {
            return fluctuationRate;
        }

        public void setFluctuationRate(String fluctuationRate) {
            this.fluctuationRate = fluctuationRate;
        }

        public String getTradeVolume() {
            return tradeVolume;
        }

        public void setTradeVolume(String tradeVolume) {
            this.tradeVolume = tradeVolume;
        }

        public String getInstitutionAccumulated() {
            return institutionAccumulated;
        }

        public void setInstitutionAccumulated(String institutionAccumulated) {
            this.institutionAccumulated = institutionAccumulated;
        }

        public String getInstitutionDailyNetTradeVolume() {
            return institutionDailyNetTradeVolume;
        }

        public void setInstitutionDailyNetTradeVolume(String institutionDailyNetTradeVolume) {
            this.institutionDailyNetTradeVolume = institutionDailyNetTradeVolume;
        }

        public String getForeignerAccumulated() {
            return foreignerAccumulated;
        }

        public void setForeignerAccumulated(String foreignerAccumulated) {
            this.foreignerAccumulated = foreignerAccumulated;
        }

        public String getForeignerDailyNetTradeVolume() {
            return foreignerDailyNetTradeVolume;
        }

        public void setForeignerDailyNetTradeVolume(String foreignerDailyNetTradeVolume) {
            this.foreignerDailyNetTradeVolume = foreignerDailyNetTradeVolume;
        }

        public String getLimitExhaustionRate() {
            return limitExhaustionRate;
        }

        public void setLimitExhaustionRate(String limitExhaustionRate) {
            this.limitExhaustionRate = limitExhaustionRate;
        }
    }
}
