package com.tcs.swiftht.SepaKYC.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${app.apiUrl}")
    private String kycReportUrl;

    public String getKycReportUrl() {
        return kycReportUrl;
    }
}
