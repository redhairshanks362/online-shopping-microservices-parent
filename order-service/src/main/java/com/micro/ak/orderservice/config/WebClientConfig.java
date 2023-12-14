package com.micro.ak.orderservice.config;

import com.micro.ak.orderservice.OrderServiceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

//    public static void main(String[] args) {
//
//        SpringApplication.run(OrderServiceApplication.class, args);
//
//    }

}
