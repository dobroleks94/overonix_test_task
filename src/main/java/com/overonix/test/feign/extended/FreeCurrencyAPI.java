package com.overonix.test.feign.extended;

import com.overonix.test.feign.ExchangerAPI;
import com.overonix.test.model.api_dto.historical_data.RangeHistorical;
import com.overonix.test.model.api_dto.latest_rate.FreeCurrencyApiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@FeignClient(url = "https://freecurrencyapi.net", name = "FreeCurrency")
public interface FreeCurrencyAPI extends ExchangerAPI {
    @RequestMapping(value = "/api/v2/latest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    FreeCurrencyApiDTO getData(@RequestParam("apikey") String apiKey,
                               @RequestParam(required = false, name = "base_currency") String currency,
                               @RequestHeader("User-Agent") String userAgent);
    @RequestMapping(value = "/api/v2/historical", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    RangeHistorical getHistorical(@RequestParam("apikey") String apiKey,
                                  @RequestParam(required = false, name = "base_currency") String currency,
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                  @RequestParam(required = false, name = "date_from") LocalDate from,
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                  @RequestParam(required = false, name = "date_to") LocalDate to,
                                  @RequestHeader("User-Agent") String userAgent);
}
