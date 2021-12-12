package com.overonix.test.feign.extended;

import com.overonix.test.feign.ExchangerCodesAPI;
import com.overonix.test.model.api_dto.available_codes.ExchangeRateCodes;
import com.overonix.test.model.api_dto.latest_rate.ExchangeRateApiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(url = "https://v6.exchangerate-api.com", name = "ExchangeRate")
public interface ExchangeRateAPI extends ExchangerCodesAPI {
    @RequestMapping(value = "/v6/{apiKey}/latest/{currency}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ExchangeRateApiDTO getData(@PathVariable String apiKey,
                               @PathVariable String currency,
                               @RequestHeader("User-Agent") String userAgent);
    @RequestMapping(value = "/v6/{apiKey}/codes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ExchangeRateCodes getCodes(@PathVariable String apiKey,
                               @RequestHeader("User-Agent")String userAgent);
}
