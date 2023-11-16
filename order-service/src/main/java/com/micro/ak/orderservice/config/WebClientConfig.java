package com.micro.ak.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    //Define a bean of type web client
    @Bean
    public WebClient webClient(){
        //It will create a bean of type web client
        return WebClient.builder().build();
        //We will use this web client bean in Order Service
    }

}
