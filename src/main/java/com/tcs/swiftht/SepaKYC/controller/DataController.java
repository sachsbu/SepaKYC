package com.tcs.swiftht.SepaKYC.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @RequestMapping("/")
    public String HelloWorld(){
        return "Hello WOrld";
    }
}
