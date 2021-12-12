package com.overonix.test.model.api_dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueryField {
    private String apikey;
    private long timestamp;
    @JsonProperty("base_currency")
    private String currency;
    @JsonProperty("date_from")
    private LocalDate from;
    @JsonProperty("date_to")
    private LocalDate to;
}
