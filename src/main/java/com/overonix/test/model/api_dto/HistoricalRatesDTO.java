package com.overonix.test.model.api_dto;

import com.overonix.test.model.enums.DataSupplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class HistoricalRatesDTO {
    private DataSupplier appliedAPI;
    private String currency;
    private Map<LocalDate, Map<String, Double>> historicalRates;
}
