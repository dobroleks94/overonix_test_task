package com.overonix.test.controller;

import com.overonix.test.model.api_dto.AvailableRatesDTO;
import com.overonix.test.model.ExchangeRate;
import com.overonix.test.model.api_dto.HistoricalRatesDTO;
import com.overonix.test.model.enums.DataSupplier;
import com.overonix.test.services.ApiService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@AllArgsConstructor
@RequestMapping("/exchange/rate")
public class ExchangeRateController {

    private ApiService apiService;

    @RequestMapping(value = "/{apiName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ExchangeRate fetchExchangeRate(@PathVariable String apiName,
                                          @RequestParam(required = false) String currency){
        DataSupplier api = DataSupplier.getByApiName(apiName);
        if (currency == null || api.equals(DataSupplier.EXCHANGE_IO_API)) { currency = api.getDefaultCurrency(); }
        return apiService.fetchData(api, currency);
    }

    @RequestMapping(value = "/{apiName}/available/rates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public AvailableRatesDTO supportedCodes(@PathVariable String apiName) {
        return apiService.fetchCurrencyCodes(DataSupplier.getByApiName(apiName));
    }

    @RequestMapping(value = "/historical/from/{from}/to/{to}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HistoricalRatesDTO historicalRatesRange(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate from,
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate to,
                                                   @RequestParam(required = false) String currency) {
        return apiService.searchHistoricalData(from, to, currency);
    }
    @RequestMapping(value = "/historical/on/{date}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HistoricalRatesDTO historicalRatesForDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable LocalDate date,
                                                     @RequestParam(required = false) String currency) {
        currency = null; // The 'api.exchangeratesapi.io' API doesn't provide the possibility to find historical data
                        //                  by currency for applied TRIAL subscription
        return apiService.searchHistoricalData(date, currency);
    }
}
