package com.example.trial;

import com.example.trial.giphyclient.GifPictureClient;
import com.example.trial.giphyclient.GiphyResponse;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class TestGiphyApiResponse {
    @Autowired
    GifPictureClient gifPictureClient;

    @Value("${GiphyApiAppId}")
    private String giphyApiKey;

    @Test
    public void ValidResponseTest(){
        GiphyResponse response = gifPictureClient.getGifPicture(giphyApiKey, "broke");
        assertTrue(response.isValid());
    }

    @Test
    public void CorrectAddressTest(){
        ArrayList<String> gifs = new ArrayList<>();
        Logger logger = LoggerFactory.getLogger(TestCurrencyApiResponse.class);
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

        GiphyResponse response = gifPictureClient.getGifPicture(giphyApiKey, "broke");
        assertTrue(gifs.contains(response.getOriginal()));
    }

}
