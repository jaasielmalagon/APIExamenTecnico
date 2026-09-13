package com.example.APIExamenTecnico.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealtCheckController {

    @GetMapping("/hello")
    private String hello() {
        return "Hello, this is working!";
    }
}
