package com.overonix.test.repositories;

import com.overonix.test.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findByTimestampAndCurrency(LocalDateTime timestamp, String currency);
}
