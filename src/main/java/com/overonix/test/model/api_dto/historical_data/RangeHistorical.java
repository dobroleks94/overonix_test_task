package com.overonix.test.model.api_dto.historical_data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.overonix.test.model.api_dto.QueryField;
import com.overonix.test.model.api_dto.latest_rate.ApiRatesRef;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class RangeHistorical implements ApiRatesRef {
    private QueryField query;
    @JsonProperty("data")
    private Map<LocalDate, Map<String, Double>> rates;

    @Override
    public String getCurrency() {
        return this.query.getCurrency();
    }

    @Override
    public long getTimestamp() {
        return this.query.getTimestamp();
    }
}
