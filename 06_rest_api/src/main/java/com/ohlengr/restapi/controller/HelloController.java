package com.ohlengr.restapi.controller;

import com.ohlengr.restapi.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
    
    @GetMapping("/message")
    public Message message(){
        return new Message("Hello World", "Ohlengr");
    }
}
