package com.example.trial;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "simple-client", url = "http://localhost")
public interface PersonClient {

    @GetMapping("/newperson")
    Person getPerson();
}
