package com.secureeureka.secureclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@EnableEurekaClient
@SpringBootApplication
public class SecureclientApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SecureclientApplication.class, args);

        String eurekaURL="localhost";
        URL obj = new URL("http://"+eurekaURL+":8090/eureka/apps/java_eureka_client");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        //con.setRequestMethod("GET");
        con.setRequestMethod("POST");
        //add request header
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);
        String inputJson = "{ \"instance\": {  \"instanceId\": \"localhost:8097\",\"hostName\": \"localhost\", \"app\": \"java_eureka_client\", \"vipAddress\": \"java_eureka_client\", \"secureVipAddress\": \"java_eureka_client\", \"status\": \"UP\", \"port\": { \"$\": \"8097\", \"@enabled\": \"true\" }, \"securePort\": { \"$\": \"443\", \"@enabled\": \"false\" }, \"healthCheckUrl\": \"http://localhost:8097/health\", \"statusPageUrl\": \"http://localhost:8097/info\", \"homePageUrl\": \"http://localhost:8097\", \"dataCenterInfo\": { \"@class\": \"com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo\", \"name\": \"MyOwn\" } } }";
        OutputStream os = con.getOutputStream();
        os.write(inputJson.getBytes());
        os.flush();
    }


}
