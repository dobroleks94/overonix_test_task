package com.overonix.test.configuration;

import com.overonix.test.model.enums.DataSupplier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfig {
    @Value("${exchange.apikeys.free-currency}")
    private String freeCurrencyApiKey;
    @Value("${exchange.apikeys.exchange-io}")
    private String exchangeIOApiKey;
    @Value("${exchange.apikeys.exchangerate-api}")
    private String exchangeRateApiKey;

    @Bean(name = "apiKeyMapper")
    public Map<DataSupplier, String> apiKeyMapper(){
        return new HashMap<DataSupplier, String>(){
            {
                put(DataSupplier.EXCHANGE_IO_API, exchangeIOApiKey);
                put(DataSupplier.EXCHANGE_RATE_API, exchangeRateApiKey);
                put(DataSupplier.FREE_CURRENCY_API, freeCurrencyApiKey);
            }
        };
    }
}
