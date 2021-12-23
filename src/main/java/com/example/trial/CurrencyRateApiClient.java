package com.example.trial;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "currencyRateApiClient", url = "${currencyApiAddress}")
public interface CurrencyRateApiClient {

    @GetMapping("/historical/{date}.json?app_id={app_id}&symbols={symbols}")
    CurrencyRate getHistorical(@PathVariable(value = "date") String Date,
                               @PathVariable(value = "symbols") String symbols,
                               @PathVariable(value = "app_id") String appId);

    @GetMapping("/latest.json?app_id={app_id}")
    CurrencyRate getLatest(@PathVariable (value = "app_id") String appId);
}
