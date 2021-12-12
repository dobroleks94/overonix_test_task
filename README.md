# API for retrieving exchange rate info
### (Overonix test task)
________________________________________
This is an API for retrieving exchange rate info. </br>

## Brief usage description
There are 3 online services providing API to fetch exchange data used here:

1. https://freecurrencyapi.net/ (apiName - **free-currency**);
2. https://exchangeratesapi.io/ (apiName - **exchange-io**);
3. https://www.exchangerate-api.com (apiName - **exchange-rate**). 

API supplies endpoints to retrieve exchange rate:

- `/exchange/rate/{apiName}` - to get the latest exchange rate from specific API provided above.
    - Possible to specify the request parameter `currency` to get rates for specific currency.
    - The default currency is USD
    > **EXAMPLE**: </br>
    > - /exchange/rate/free-currency
    > - /exchange/rate/free-currency?currency=UAH
- `/exchange/rate/{apiName}/available/rates` - to get all available currency codes and currency names.
    > **EXAMPLE**: </br>
    > - /exchange/rate/exchange-io/available/rates
- `/exchange/rate/historical/from/{start}/to/{end}` - to get exchange rate history for specific period from {start} date to {end} date.
    - The request parameter `currency` is possible to be specified either.
    - The default currency is provided by vendor APIs
    > **EXAMPLE**: </br>
    > - /exchange/rate/historical/from/2007-01-01/to/2008-01-01
    > - /exchange/rate/historical/from/2007-01-01/to/2008-01-01?currency=EUR
- `/exchange/rate/historical/on/{date}` - to get exchange rates for particular day
    > **EXAMPLE**: </br>
    > - /exchange/rate/historical/on/2021-12-01

## Installation requirements
The Docker and Docker-Compose are necessary to be installed. To run the API you need solely download `docker-compose.yml` [file](https://github.com/dobroleks94/overonix_test_task/blob/master/docker/docker-compose.yml) and run `docker-compose up -d`.
The API will be deployed on port `8094`