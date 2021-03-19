package com.tcs.swiftht.SepaKYC.controller;

import com.tcs.swiftht.SepaKYC.config.AppConfig;

import com.tcs.swiftht.SepaKYC.service.DataScreeningService;
import com.tcs.swiftht.SepaKYC.service.DataScreeningServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;



@RestController
public class DataController {

    @Autowired
    private AppConfig appConfig;

    private DataScreeningService dataScreeningService = new DataScreeningServiceImpl();


    @RequestMapping("/")
    public String HelloWorld() throws IOException {

        // Sending get request
        String resp = dataScreeningService.connectKYCRegistry(appConfig.getUsername(), appConfig.getPassword());

       return resp;
    }


}
