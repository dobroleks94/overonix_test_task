package com.overonix.test.model.api_dto.available_codes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class ExchangeIOCodes implements ApiCodesRef{
    @JsonProperty("symbols")
    private Map<String, String> currencyCodes;
}
