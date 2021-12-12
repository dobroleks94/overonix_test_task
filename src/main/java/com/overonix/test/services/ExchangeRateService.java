package com.overonix.test.services;

import com.overonix.test.model.ExchangeRate;
import com.overonix.test.repositories.ExchangeRateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExchangeRateService {

    private final ExchangeRateRepository repository;

    @Transactional
    public ExchangeRate save(ExchangeRate exchangeRateData) {
        exchangeRateData.getRates().forEach(rate -> rate.setRelatedRate(exchangeRateData));
        return repository.save(exchangeRateData);
    }

    public Optional<ExchangeRate> findByTimestampAndCurrency(LocalDateTime localDateTime, String currency){
        return repository.findByTimestampAndCurrency(localDateTime, currency);
    }
}
