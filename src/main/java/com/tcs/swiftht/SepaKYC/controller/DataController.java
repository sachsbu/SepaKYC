package com.tcs.swiftht.SepaKYC.controller;

import com.tcs.swiftht.SepaKYC.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @Autowired
    private AppConfig appConfig;


    @RequestMapping("/")
    public String HelloWorld(){
        return appConfig.getKycReportUrl();
    }
}
