package com.overonix.test.model.api_dto.latest_rate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ExchangeIOApiDTO implements ApiRatesRef {
    private long timestamp;
    @JsonProperty("base")
    private String currency;
    private Map<String, Double> rates;
}
