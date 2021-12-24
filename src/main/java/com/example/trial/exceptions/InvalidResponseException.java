package com.example.trial.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidResponseException extends Exception {
    private final String request;

    public InvalidResponseException(String request) {
        super(String.format("Invalid response for request \"%s\"", request));
        this.request = request;
    }
}
