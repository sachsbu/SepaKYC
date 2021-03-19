package com.tcs.swiftht.SepaKYC.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${app.apiUrl}")
    private String kycReportUrl;

    @Value("${security.oauth2.client.client-id}")
    private String appKey;

    @Value("${security.oauth2.client.client-secret}")
    private String appSecret;

    @Value("${security.oauth2.client.username}")
    private String username;

    @Value("${security.oauth2.client.password}")
    private String password;

    @Value("${security.oauth2.client.grant-type}")
    private String grant_type;

    @Value("${security.oauth2.client.access-token-uri}")
    private String tokenUri;

    public String getTokenUri() {
        return tokenUri;
    }

    public String getKycReportUrl() {
        return kycReportUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGrant_type() {
        return grant_type;
    }
}
