package com.micro.ak.discoveryserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//To make this into eureka discovery sevrer we will add this annotation
@EnableEurekaServer
public class DiscoveryServerApplication {
    public static void main(String[] args){
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }
}


