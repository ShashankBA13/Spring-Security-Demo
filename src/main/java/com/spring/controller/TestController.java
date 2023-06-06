package com.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/home")
    public String doStuff() {
        return "Hello from doStuff of TestController";
    }

    @GetMapping("/admin")
    public String doAdmin() {
        return "A secret message from the admin: ";
    }

    @GetMapping("/test")
    public String doTest() {
        return "A message from the test: You are inside the test method ";
    }
}