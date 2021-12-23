package com.example.trial;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gifclient", url = "http://api.giphy.com/v1/gifs")
public interface gifPictureClient {

    @GetMapping("/search?api_key={api_key}&q={query}&limit=20&offset=0")
    GiphyResponse getGifPicture(@PathVariable(name = "api_key") String apikey,@PathVariable(name = "query") String query);
    //Map<String, Object> getGifPicture(@PathVariable(name = "query") String query);

}
