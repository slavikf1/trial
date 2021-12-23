package com.example.trial;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GiphyResponse {

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class GiphyData {
        @JsonIgnoreProperties(ignoreUnknown = true)
        static class GiphyImages {
            public GiphyImages(){}
            @JsonProperty("original")
            Map <String, String> original;
            public String getUrl(){
                return this.original.get("url");
            }
        }
        public GiphyData() {
        }
        @JsonProperty("url")
        String url;
        @JsonProperty("embed_url")
        String embed;
        @JsonProperty("images")
        GiphyImages images;

        public String getEmbed() {
            return embed;
        }

        public String getUrl(){
            return images.getUrl();
        }


        }

    @JsonProperty("data")
    ArrayList<GiphyData> data;
    @JsonProperty("meta")
    Map<String, String> meta;

    public GiphyResponse(){}
    public String getOriginal(){
        return data.get(new Random().nextInt(data.size()-1)).getUrl();
    }
    public boolean isValid(){
        return this.meta.get("status").equals("200");
    }
}
