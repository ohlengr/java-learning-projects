package com.ohlengr.restapi.controller;

import com.ohlengr.restapi.model.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World";
    }
    
    @GetMapping("/api/message")
    public Message helloJson(){
        return new Message("Hello World", "Ohlengr");
    }
}
