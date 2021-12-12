package com.overonix.test.feign.extended;

import com.overonix.test.feign.ExchangerCodesAPI;
import com.overonix.test.model.api_dto.available_codes.ExchangeIOCodes;
import com.overonix.test.model.api_dto.historical_data.DailyHistorical;
import com.overonix.test.model.api_dto.latest_rate.ExchangeIOApiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@FeignClient(url = "http://api.exchangeratesapi.io", name = "ExchangeIO")
public interface ExchangeIOAPI extends ExchangerCodesAPI {
    @RequestMapping(value = "/v1/latest", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ExchangeIOApiDTO getData(@RequestParam("access_key") String apiKey,
                             @RequestParam(name = "base", required = false) String currency,
                             @RequestHeader("User-Agent") String userAgent);

    @RequestMapping(value = "/v1/symbols", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ExchangeIOCodes getCodes(@RequestParam("access_key") String apiKey,
                             @RequestHeader("User-Agent")String userAgent);

    @RequestMapping(value = "/v1/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    DailyHistorical getHistorical(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                  @PathVariable LocalDate date,
                                  @RequestParam("access_key") String apiKey,
                                  @RequestParam(name = "base", required = false) String currency,
                                  @RequestHeader("User-Agent") String userAgent);
}
