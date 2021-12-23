package com.example.trial;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CheckCurrencyApiResponse {

    @Autowired
    CurrencyRateService currencyRateService;

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Test
    @DisplayName("Should throw Exception upon Empty response")
    public void testCurrencyApiClient(){
        //preparing the non-existing Symbols
        String testSymbols = "XXX";
        //executing response to non-existing symbols
        assertThrows(EmptyResponseException.class, () -> currencyRateService.compareCurrentAndHistoricalRate(testSymbols));
    }


}
