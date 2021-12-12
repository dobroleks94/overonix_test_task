package com.overonix.test.model.api_dto.available_codes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class ExchangeRateCodes implements ApiCodesRef{
    @JsonProperty("supported_codes")
    private Set<String[]> supportedCodes;

    @Override
    public Map<String, String> getCurrencyCodes() {
        Map<String, String> codesMap = new HashMap<>();
        supportedCodes.forEach(codes -> codesMap.put(codes[0], codes[1]));
        return codesMap;
    }
}
