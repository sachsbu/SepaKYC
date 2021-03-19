package com.tcs.swiftht.SepaKYC.service;

import com.tcs.swiftht.SepaKYC.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataScreeningServiceImpl implements DataScreeningService{

    private static final Pattern pat = Pattern.compile(".*\"access_token\"\\s*:\\s*\"([^\"]+)\".*");

    @Override
    public String connectKYCRegistry(String user, String pass) {
        String token = getResourceCredentials(user, pass);

        return usePersonSummary(token, "folders/BITIAAAXXXX");
    }


    private String getAuthentication(){
        String auth = "mNZ2kZUqLvRL4ys55BeMaBqFwDp4HAiH" + ":" + "2xFKdbqv1rh4j9Ej";
        return Base64.getEncoder().encodeToString(auth.getBytes());
    }
    private String getResourceCredentials(String userName, String password){
        String content = "grant_type=password&username=" + userName + "&password=" + password;
        BufferedReader reader = null;
        HttpsURLConnection connection = null;
        String returnValue = "";
        try {
            URL url = new URL("https://sandbox.swift.com/oauth2/v1/token");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            System.out.println(getAuthentication());
            connection.setRequestProperty("Authorization", "Basic " + getAuthentication());
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "application/json");
            PrintStream os = new PrintStream(connection.getOutputStream());
            os.print(content);
            os.close();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            String response = out.toString();
            Matcher matcher = pat.matcher(response);
            if (matcher.matches() && matcher.groupCount() > 0) {
                returnValue = matcher.group(1);
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
            connection.disconnect();
        }
        return returnValue;
    }


    private String usePersonSummary(String resourceOwner, String netId) {
        BufferedReader reader = null;
        String response="";
        try {
            URL url = new URL("https://sandbox.swift.com/kyc/v1/entities/" + netId);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", "Bearer " + resourceOwner);
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            StringWriter out = new StringWriter(connection.getContentLength() > 0 ? connection.getContentLength() : 2048);
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
             response = out.toString();
            System.out.println(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
