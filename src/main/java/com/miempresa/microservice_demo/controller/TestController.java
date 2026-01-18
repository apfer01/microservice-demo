package com.miempresa.microservice_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "¡Hola mundo! ¿Que tal estas?";
    }
}