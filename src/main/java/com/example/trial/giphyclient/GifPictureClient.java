package com.example.trial.giphyclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gifclient", url = "${giphyApiAddress}")
public interface GifPictureClient {

    @GetMapping("/search?api_key={api_key}&q={query}&limit=20&offset=0")
    GiphyResponse getGifPicture(@PathVariable(name = "api_key") String apikey, @PathVariable(name = "query") String query);
    //Map<String, Object> getGifPicture(@PathVariable(name = "query") String query);

}
