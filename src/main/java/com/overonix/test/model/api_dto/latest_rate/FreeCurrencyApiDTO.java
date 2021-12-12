package com.overonix.test.model.api_dto.latest_rate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.overonix.test.model.api_dto.QueryField;
import lombok.Data;

import java.util.Map;

@Data
public class FreeCurrencyApiDTO implements ApiRatesRef {
    private QueryField query;
    @JsonProperty("data")
    private Map<String, Double> rates;

    public String getCurrency() {
        return this.query.getCurrency();
    }
    public long getTimestamp() {
        return this.query.getTimestamp();
    }

}
