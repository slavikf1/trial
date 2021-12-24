package com.example.trial.controller;

import com.example.trial.currencyservice.CurrencyRateService;
import com.example.trial.exceptions.EmptyResponseException;
import com.example.trial.exceptions.InvalidResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/broke")
public class BrokeController {

    private final CurrencyRateService currencyRateService;
    Logger logger = LoggerFactory.getLogger(BrokeController.class);

    @Autowired
    public BrokeController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }


    @RequestMapping(value = "/{symbols}", method =  RequestMethod.GET)
    public String BrokeOfRich (@PathVariable String symbols, Model model) throws EmptyResponseException, InvalidResponseException {
        logger.trace("A broke request has came in with currency code: {}", symbols);
        String address = currencyRateService.RateGif(symbols);
        model.addAttribute("address", address);
        logger.trace("found a suitable gif with address: {}", address);
        return "brokeOrRich";
    }
}


