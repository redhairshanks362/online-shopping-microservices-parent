package com.micro.ak.orderservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    //Define a bean of type web client
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder(){
        //It will create a bean of type web client
        return WebClient.builder();
        //We will use this web client bean in Order Service
    }

}
