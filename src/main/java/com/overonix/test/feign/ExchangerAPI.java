package com.overonix.test.feign;

import com.overonix.test.model.api_dto.latest_rate.ApiRatesRef;

/**
 * General common interface to ensure abstraction in switching among APIs
 */
public interface ExchangerAPI {
    ApiRatesRef getData(String apiKey, String currency, String userAgent);
}
