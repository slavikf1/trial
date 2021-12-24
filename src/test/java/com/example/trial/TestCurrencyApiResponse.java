package com.example.trial;

import com.example.trial.currencyservice.CurrencyRateService;
import com.example.trial.exceptions.EmptyResponseException;
import com.example.trial.exceptions.InvalidResponseException;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@WireMockTest(httpPort = 8080)
@SpringBootTest
public class TestCurrencyApiResponse {

    @Autowired
    CurrencyRateService currencyRateService;

    @Test
    @DisplayName("Should throw Exception upon Empty response")
    public void testEmptyCurrencyApiResponse(){

        String testSymbols = "EMPT";

        //executing request to non-existing symbols
        assertThrows(EmptyResponseException.class, () -> currencyRateService.compareCurrentAndHistoricalRate(testSymbols));
    }

    @Test
    @DisplayName("Should return -1 comparison result of lower currency")
    public void testLowerCurrencyResponse(){
        Logger logger = LoggerFactory.getLogger(TestCurrencyApiResponse.class);
        String testSymbols = "LOWER";
        try {
            assertEquals(-1, currencyRateService.compareCurrentAndHistoricalRate(testSymbols));
        }
        catch (EmptyResponseException e){
            logger.error(e.getMessage());
        }
    }

    @Test
    @DisplayName("Testing higher currency returns 1 comparison result")
    public void testHigherCurrencyResponse(){
        Logger logger = LoggerFactory.getLogger(TestCurrencyApiResponse.class);
        String testSymbols = "HIGHER";
        try {

            assertEquals(1, currencyRateService.compareCurrentAndHistoricalRate(testSymbols));
        }
        catch (EmptyResponseException e){
            logger.error(e.getMessage());
        }
    }
    @Test
    @DisplayName("Should return 0 comparison result of equal currency")
    public void testEqualCurrencyResponse(){
        Logger logger = LoggerFactory.getLogger(TestCurrencyApiResponse.class);
        String testSymbols = "EQUAL";
        try {

            assertEquals(0, currencyRateService.compareCurrentAndHistoricalRate(testSymbols));
        }
        catch (EmptyResponseException e){
            logger.error(e.getMessage());
        }
    }
    @Test
    @DisplayName("Should return \"Broke\" gif for lower currency")
    public void testLowerCurrencyGif(){
        String testSymbols = "LOWER";

        Logger logger = LoggerFactory.getLogger(TestCurrencyApiResponse.class);
        ArrayList<String> gifs = new ArrayList<>();

        try {
            Resource resource = new ClassPathResource("broke_gifs");
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                gifs.add(scanner.next());
            }
        }
        catch (IOException e){
            logger.error(e.getMessage());
        }

        try {
            String gifAddress =  currencyRateService.RateGif(testSymbols);
            assertTrue(gifs.contains(gifAddress));
        }
        catch (EmptyResponseException | InvalidResponseException e){
            logger.error(e.getMessage());
        }
    }
    @Test
    @DisplayName("Should return \"Rich\" gif for higher currency")
    public void testHigherCurrencyGif(){
        String testSymbols = "HIGHER";

        Logger logger = LoggerFactory.getLogger(TestCurrencyApiResponse.class);
        ArrayList<String> gifs = new ArrayList<>();

        try {
            Resource resource = new ClassPathResource("rich_gifs");
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                gifs.add(scanner.next());
            }
        }
        catch (IOException e){
            logger.error(e.getMessage());
        }

        try {
            String gifAddress =  currencyRateService.RateGif(testSymbols);
            assertTrue(gifs.contains(gifAddress));
        }
        catch (EmptyResponseException | InvalidResponseException e){
            logger.error(e.getMessage());
        }
    }
}
