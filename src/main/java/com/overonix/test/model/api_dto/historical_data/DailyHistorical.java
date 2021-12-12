package com.overonix.test.model.api_dto.historical_data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;


@Data
public class DailyHistorical {
    @JsonProperty("base")
    private String currency;
    private LocalDate date;
    private Map<String, Double> rates;
}
