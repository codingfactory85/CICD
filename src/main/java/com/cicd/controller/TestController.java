package com.cicd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/health")
public class TestController {

    @GetMapping("")
    private String getHealth(){
        return "health UP ";
    }
}
