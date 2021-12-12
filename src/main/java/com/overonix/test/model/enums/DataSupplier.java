package com.overonix.test.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DataSupplier {
    FREE_CURRENCY_API("com.overonix.test.feign.extended.FreeCurrencyAPI", "free-currency", "USD"),
    EXCHANGE_IO_API("com.overonix.test.feign.extended.ExchangeIOAPI", "exchange-io", null),
    EXCHANGE_RATE_API("com.overonix.test.feign.extended.ExchangeRateAPI", "exchange-rate", "USD");

    private final String beanName;
    private final String apiName;
    private final String defaultCurrency;

    @Override
    public String toString() {
        return getBeanName();
    }
    public static DataSupplier getByApiName(String apiName){
        return Arrays.stream(DataSupplier.values())
                .filter(supplier -> apiName.equals(supplier.getApiName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No such data supplier provided!"));
    }
}
