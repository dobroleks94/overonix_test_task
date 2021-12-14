package com.overonix.test;

import com.overonix.test.model.ExchangeRate;
import com.overonix.test.model.Rate;
import com.overonix.test.model.api_dto.latest_rate.ExchangeRateApiDTO;
import com.overonix.test.model.enums.DataSupplier;
import com.overonix.test.repositories.ExchangeRateRepository;
import com.overonix.test.services.ApiService;
import com.overonix.test.services.ExchangeRateService;
import com.overonix.test.utils.DTO2ExchangeRateConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@SpringBootTest
class TestOveronixApplicationTests {

    ExchangeRateService service;
    @Autowired
    ExchangeRateRepository repository;
    @Autowired
    ApiService apiService;
    DTO2ExchangeRateConverter converter;

    @BeforeEach
    public void setup() throws Exception {
        service = new ExchangeRateService(repository);
        converter = PowerMockito.spy(new DTO2ExchangeRateConverter());
    }

    @Test
    void should_successfully_save_ExchangeRate_with_appropriate_id() {
        ExchangeRate rate = ExchangeRate.builder()
                .id(1L)
                .currency("EUR")
                .timestamp(LocalDateTime.now())
                .rates(new HashSet<Rate>(){{add(Rate.builder().name("TestRate").value(0.0).build());}})
                .build();

        assertThat(service.save(rate).getId(), equalTo(rate.getId()));
    }

    @Test
    void should_return_FreeCurrency_data_supplier_on_demand(){
        assertThat(
                apiService.fetchData(DataSupplier.FREE_CURRENCY_API, "UAH").getApi(),
                equalTo(DataSupplier.FREE_CURRENCY_API)
        );
    }

    @Test
    void should_be_instance_of_ExchangeRate_after_convertion(){
        ExchangeRateApiDTO erDTO = new ExchangeRateApiDTO();
        erDTO.setCurrency("UAH");
        erDTO.setTimestamp(12341234L);
        erDTO.setRates(new HashMap<String, Double>() {{put("TestCurrency", 8.0);}});

        assertThat(
                converter.convertRates(erDTO),
                instanceOf(ExchangeRate.class)
        );
    }

    @Test
    void should_be_not_null_after_convertion() {

        ExchangeRateApiDTO er = new ExchangeRateApiDTO();
        er.setRates(Collections.emptyMap());

        assertThat(
                converter.convertRates(er),
                notNullValue()
        );
    }

}
