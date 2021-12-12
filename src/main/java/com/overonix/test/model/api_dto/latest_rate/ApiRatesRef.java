package com.overonix.test.model.api_dto.latest_rate;

import java.util.Map;

public interface ApiRatesRef {
    String getCurrency();
    long getTimestamp();
    Map getRates();
}
