package com.overonix.test.feign;

import com.overonix.test.model.api_dto.available_codes.ApiCodesRef;

/**
 * Subordinate interface to ensure abstraction in switching between APIs,
 *              which have endpoint to get currency codes
 */
public interface ExchangerCodesAPI extends ExchangerAPI {
    ApiCodesRef getCodes(String apiKey, String userAgent);
}
