package com.overonix.test.model.api_dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@AllArgsConstructor
@Builder
public class AvailableRatesDTO {
    private String apiName;
    private long count;
    private Set<CurrencyRepres> currencies;

    @Data
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CurrencyRepres {
        private String code;
        private String name;
    }
}
