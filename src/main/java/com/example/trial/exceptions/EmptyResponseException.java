package com.example.trial.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class EmptyResponseException extends Exception{

    private final String symbols;

    public EmptyResponseException(String symbols) {
        super("Empty response for a currency code " + symbols);
        this.symbols = symbols;
    }

    public String getSymbols() {
        return symbols;
    }
}
