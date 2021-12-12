package com.overonix.test.utils;

import com.overonix.test.model.api_dto.AvailableRatesDTO;
import com.overonix.test.model.ExchangeRate;
import com.overonix.test.model.Rate;
import com.overonix.test.model.api_dto.available_codes.ApiCodesRef;
import com.overonix.test.model.api_dto.latest_rate.ApiRatesRef;
import com.overonix.test.model.enums.DataSupplier;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Component
public class DTO2ExchangeRateConverter {

    public ExchangeRate convertRates(ApiRatesRef fetchedDTO) {
        return ExchangeRate.builder()
                .currency(fetchedDTO.getCurrency())
                .timestamp(LocalDateTime.ofInstant(Instant.ofEpochSecond(fetchedDTO.getTimestamp()), TimeZone.getDefault().toZoneId()))
                .rates(fetchRates(fetchedDTO.getRates()))
                .build();
    }

    public AvailableRatesDTO convertCodes(DataSupplier apiName, ApiCodesRef apiCodes) {
        return AvailableRatesDTO.builder()
                .apiName(apiName.getApiName())
                .currencies(
                        apiCodes.getCurrencyCodes().entrySet().stream()
                                .map(entry -> new AvailableRatesDTO.CurrencyRepres(entry.getKey(), entry.getValue()))
                                .collect(Collectors.toSet()) )
                .build();
    }

    private Set<Rate> fetchRates(Map<String, Double> ratesMap){
        return ratesMap.entrySet().stream()
                .map(entry ->
                        Rate.builder()
                                .name(entry.getKey())
                                .value(entry.getValue())
                                .build()
                )
                .collect(Collectors.toSet());
    }

}
