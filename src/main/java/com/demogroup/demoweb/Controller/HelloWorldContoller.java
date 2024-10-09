package com.demogroup.demoweb.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldContoller {
    @GetMapping("/api/hello")
    public String test() {
        return "Hello, world!";
    }
}
