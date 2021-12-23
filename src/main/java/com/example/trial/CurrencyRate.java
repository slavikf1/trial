package com.example.trial;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRate {
    @JsonProperty("base")
    private String baseCurrency;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("rates")
    private Map <String, Double> rates;

    @Override
    public String toString(){
        return String.format("%d to %s", rates.values().toArray()[0], baseCurrency);
    }
    public Double getRate(String symbols){
        return this.rates.get(symbols);
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    boolean isEmpty(){
        return (rates.isEmpty());
    }

}
