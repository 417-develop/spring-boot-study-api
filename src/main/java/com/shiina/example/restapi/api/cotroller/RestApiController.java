package com.shiina.example.restapi.api.cotroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @GetMapping("/")
    public String get(){
        return "hello world";
    }
}