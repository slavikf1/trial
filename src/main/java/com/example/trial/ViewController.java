package com.example.trial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class ViewController {

    private final CurrencyRateService currencyRateService;

    Logger logger = LoggerFactory.getLogger(ViewController.class);
    @Autowired
    PersonClient personClient;

    @Autowired
    gifPictureClient gifclient;

    @Autowired
    public ViewController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }


    @GetMapping("/newperson")
    public String getNewPerson(Model model){
//        return new Person(UUID.randomUUID(), "Ivan");
        Person p = new Person(UUID.randomUUID(), "Peter");
        model.addAttribute("person", p.getName());
        return "newperson";
    }

    @GetMapping("/broke")
    public String BrokeOfRich (@RequestParam String symbols, Model model) throws EmptyResponseException, InvalidResponseException{
        logger.trace("A broke request has came in with currency code: {}", symbols);
        String address = currencyRateService.RateGif(symbols);
        model.addAttribute("address", address);
        logger.trace("found a suitable gif with address: {}", address);
        return "brokeOrRich";
    }


    }


