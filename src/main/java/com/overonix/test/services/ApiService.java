package com.overonix.test.services;

import com.overonix.test.feign.ExchangerCodesAPI;
import com.overonix.test.feign.ExchangerAPI;
import com.overonix.test.feign.extended.ExchangeIOAPI;
import com.overonix.test.feign.extended.FreeCurrencyAPI;
import com.overonix.test.model.api_dto.AvailableRatesDTO;
import com.overonix.test.model.ExchangeRate;
import com.overonix.test.model.api_dto.HistoricalRatesDTO;
import com.overonix.test.model.api_dto.available_codes.ApiCodesRef;
import com.overonix.test.model.api_dto.historical_data.DailyHistorical;
import com.overonix.test.model.api_dto.historical_data.RangeHistorical;
import com.overonix.test.model.api_dto.latest_rate.ApiRatesRef;
import com.overonix.test.model.enums.DataSupplier;
import com.overonix.test.utils.DTO2ExchangeRateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApiService {

    @Value("${custom-feign.default-user-agent}")
    private String userAgent;

    private final ApplicationContext applicationContext;
    private final DTO2ExchangeRateConverter converter;
    private final Map<DataSupplier, String> apiKeyMapper;
    private final ExchangeRateService exchangeRateService;

    @Autowired
    public ApiService(ApplicationContext applicationContext,
                      DTO2ExchangeRateConverter converter,
                      Map<DataSupplier, String> apiKeyMapper,
                      ExchangeRateService exchangeRateService) {
        this.applicationContext = applicationContext;
        this.converter = converter;
        this.apiKeyMapper = apiKeyMapper;
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * Fetching data for latest currency rate
     *
     * @param apiName  - the representation of API
     * @param currency - exchange rates are fetched for this currency
     * @return
     */
    public ExchangeRate fetchData(DataSupplier apiName, String currency) {
        ExchangeRate exchangeRateData = getAPIExchangeRate(apiName, currency);
        Optional<ExchangeRate> exchangeRateDB = exchangeRateService.findByTimestampAndCurrency(exchangeRateData.getTimestamp(), currency);
        return exchangeRateDB.orElseGet(() -> exchangeRateService.save(exchangeRateData));
    }

    /**
     * Fetching currency codes
     *
     * @param apiName - the representation of API
     * @return
     */
    public AvailableRatesDTO fetchCurrencyCodes(DataSupplier apiName) {
        AvailableRatesDTO codes = (apiName.equals(DataSupplier.FREE_CURRENCY_API))
                ? customCurrencyCodesMapping(apiName) // fetching necessary data by own because of absence of API reference (https://freecurrencyapi.net)
                : createAvailableRatesDTO(apiName); // fetching data from API
        codes.setCount(codes.getCurrencies().size());
        return codes;
    }

    /**
     * ExchangeRate object is created here from fetched API data
     *
     * @param apiName
     * @param currency
     * @return
     */
    private ExchangeRate getAPIExchangeRate(DataSupplier apiName, String currency) {
        ApiRatesRef fetchedDTO = getFetchedDTO(apiName, currency);
        ExchangeRate exchangeRateData = converter.convertRates(fetchedDTO);
        exchangeRateData.setApi(apiName);
        return exchangeRateData;
    }

    /**
     * Performing searching the historical data of exchange rates during some period
     * @param from - start date
     * @param to - end date
     * @param currency - is used to specify currency for rates
     * @return
     */
    public HistoricalRatesDTO searchHistoricalData(LocalDate from, LocalDate to, String currency) {
        RangeHistorical fetchedDTO =
                ((FreeCurrencyAPI) applicationContext.getBean(DataSupplier.FREE_CURRENCY_API.getBeanName()))
                        .getHistorical(
                                this.apiKeyMapper.get(DataSupplier.FREE_CURRENCY_API),
                                currency,
                                from,
                                to,
                                userAgent
                                );
        return HistoricalRatesDTO.builder()
                .appliedAPI(DataSupplier.FREE_CURRENCY_API)
                .currency(fetchedDTO.getCurrency())
                .historicalRates(fetchedDTO.getRates())
                .build();

    }

    /**
     * Performing searching the historical data of exchange rates for specific day
     * @param date
     * @param currency
     * @return
     */
    public HistoricalRatesDTO searchHistoricalData(LocalDate date, String currency) {
        DailyHistorical fetchedDTO =
                ((ExchangeIOAPI) applicationContext.getBean(DataSupplier.EXCHANGE_IO_API.getBeanName()))
                        .getHistorical(
                                date,
                                this.apiKeyMapper.get(DataSupplier.EXCHANGE_IO_API),
                                currency,
                                userAgent
                        );
        return HistoricalRatesDTO.builder()
                .appliedAPI(DataSupplier.EXCHANGE_IO_API)
                .currency(fetchedDTO.getCurrency())
                .historicalRates(new HashMap<LocalDate, Map<String, Double>>(){{put(fetchedDTO.getDate(), fetchedDTO.getRates());}})
                .build();

    }

    private AvailableRatesDTO createAvailableRatesDTO(DataSupplier apiName) {
        ApiCodesRef apiCodes = getFetchedDTO(apiName);
        return converter.convertCodes(apiName, apiCodes);
    }

    private AvailableRatesDTO customCurrencyCodesMapping(DataSupplier apiName) {
        return AvailableRatesDTO.builder()
                .apiName(apiName.getApiName())
                .currencies(
                        fetchData(apiName, null).getRates().stream()
                                .map(rate -> new AvailableRatesDTO.CurrencyRepres(rate.getName(), null))
                                .collect(Collectors.toSet()))
                .build();
    }

    private ApiRatesRef getFetchedDTO(DataSupplier apiName, String currency) {
        return ((ExchangerAPI) applicationContext.getBean(apiName.getBeanName()))
                .getData(
                        this.apiKeyMapper.get(apiName),
                        currency,
                        userAgent
                );
    }

    private ApiCodesRef getFetchedDTO(DataSupplier apiName) {
        return ((ExchangerCodesAPI) applicationContext.getBean(apiName.getBeanName()))
                .getCodes(
                        this.apiKeyMapper.get(apiName),
                        userAgent);
    }

}
