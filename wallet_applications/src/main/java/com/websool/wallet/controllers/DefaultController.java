package com.websool.wallet.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @GetMapping
    public String home(){
        return "The Finance (V1) service is UP!";
    }
}
