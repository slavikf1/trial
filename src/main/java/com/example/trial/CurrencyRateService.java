package com.example.trial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class CurrencyRateService {

    Logger logger = LoggerFactory.getLogger(ViewController.class);

    private final CurrencyRateApiClient currencyRateApiClient;
    private final gifPictureClient gifPictureClient;
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Value("${currencyApiAppId}")
    private String AppId;

    @Value("${Giphy_api_key}")
    private String giphyApiKey;

    @Autowired
    public CurrencyRateService(CurrencyRateApiClient currencyRateApiClient, gifPictureClient gifPictureClient) {
        this.currencyRateApiClient = currencyRateApiClient;
        this.gifPictureClient = gifPictureClient;
    }

    public CurrencyRate getHistoricalRateToBase (String symbols,LocalDate date){
       CurrencyRate response = currencyRateApiClient.getHistorical(date.format(formatter), symbols, AppId);
       logger.trace("Obtained Historical currency date for code {}, the rate is {}", symbols, response.toString());
       return response;
    }

    public int compareCurrentAndHistoricalRate (String symbols) throws EmptyResponseException{
        CurrencyRate yesterday = currencyRateApiClient.getHistorical(LocalDate.now().minusDays(1).format(formatter), symbols, AppId);
        CurrencyRate latest = currencyRateApiClient.getLatest(AppId);
        if (yesterday.isEmpty() | latest.isEmpty()) {
            logger.error("Empty response for symbols {}", symbols);
            throw new EmptyResponseException(symbols);
        }
        logger.trace("Comparison for {} currency code. Yesterday's rate: {}. Today's rate: {} ",
                symbols,
                yesterday.getRate(symbols).toString(),
                latest.getRate(symbols).toString());

        return (yesterday.getRate(symbols).compareTo(latest.getRate(symbols)));
    }

    public String RateGif(String symbols) throws EmptyResponseException, InvalidResponseException{
        int comparison = compareCurrentAndHistoricalRate(symbols);

        if (comparison > 1){
            logger.trace("looking for a \"BROKE\" GIF picture");
            var result = gifPictureClient.getGifPicture(giphyApiKey, "broke");
            if (!result.isValid()) throw new InvalidResponseException("broke");
            return result.getOriginal();
        }
        else if (comparison == 0){
            //Условием задачи не описан случай равенства курсов. В данном случае возвращаем equalizer
            logger.trace("Seems rates are equal (bedtime?) looking for a \"equalizer\" GIF picture");
            var result = gifPictureClient.getGifPicture(giphyApiKey, "equalizer");
            if (!result.isValid()) throw new InvalidResponseException("equalizer");
            return result.getOriginal();
        }
        else if (comparison < 1)
            logger.trace("looking for a \"RICH\" GIF picture");
            var result = gifPictureClient.getGifPicture(giphyApiKey, "rich");
            if (!result.isValid()) throw new InvalidResponseException("rich");
            return result.getOriginal();
    }
}

