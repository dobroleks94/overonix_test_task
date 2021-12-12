package com.overonix.test.model.api_dto.latest_rate;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ExchangeRateApiDTO implements ApiRatesRef {
    @JsonProperty("time_last_update_unix")
    private long timestamp;
    @JsonProperty("base_code")
    private String currency;
    @JsonProperty("conversion_rates")
    private Map<String, Double> rates;
}
