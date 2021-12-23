package com.example.trial;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
public class PersonController {

    private final PersonAccessService personAccessService;
    private final CurrencyRateService currencyRateService;

    @Autowired
    PersonClient personClient;

    @Autowired
    gifPictureClient gifclient;

    @Value("${Giphy_api_key}")
    private String giphyApiKey;

    @Autowired
    public PersonController(PersonAccessService personAccessService, CurrencyRateService currencyRateService) {
        this.personAccessService = personAccessService;
        this.currencyRateService = currencyRateService;
    }
    @PostMapping("/person")
    public String addPerson(@RequestBody Person person){
        personAccessService.insertPerson(person);
        return "added";
    }

    @GetMapping("/person")
    public List<Person> getAllPeople() {
        return PersonAccessService.getDB();
    }



    @GetMapping("/fetch")
    public String fetch(){
        Person p = personClient.getPerson();
        System.out.println(p.getName());
        return p.getName();
    }

    @GetMapping("/gif")
    public String getGif(@RequestParam String query){
        try {
            GiphyResponse response = gifclient.getGifPicture(giphyApiKey, query);
            return response.getOriginal();
        }
        catch (FeignException e){
            return e.contentUTF8();
        }
    }

    @GetMapping("/currency")
    public String getHistoricalCurrencyRate(@RequestParam String symbols){
        return currencyRateService.getHistoricalRateToBase(symbols,LocalDate.now().minusDays(1)).toString();
    }

//    @GetMapping("/target")
//    public String BrokeOfRich (@RequestParam String symbols){
//        return currencyRateService.RateGif(symbols);
//    }
}
