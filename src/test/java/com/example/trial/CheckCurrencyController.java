package com.example.trial;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CheckCurrencyController {
    @Autowired
    CurrencyRateService currencyRateService;
    @Autowired
    CurrencyRateApiClient currencyRateApiClient;
    @Autowired
    gifPictureClient gifPictureClient;

    @Value("${currencyApiAppId}")
    private String CurrencyApiKey;

    @Value("${currencyApiAddress}")
    private String currencyApiKey;

    @Value("${Giphy_api_key}")
    private String giphyApiKey;

    @Test
    @DisplayName("Basic assertion test of the CurrencyClient")
    public void shouldReturnHistoricalRate(){
        assertNotNull(currencyRateApiClient.getLatest(CurrencyApiKey).getRates());
        }
    @Test
    @DisplayName("Basic test of the GiphyClient  - tests response 200 status")
    public void shouldReturnOkStatus(){
        assertTrue(gifPictureClient.getGifPicture(giphyApiKey, "broke").isValid());
    }

}


