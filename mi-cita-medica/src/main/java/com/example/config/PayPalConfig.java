package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.paypal.base.rest.APIContext;

@Configuration
public class PayPalConfig {

    @Bean
    public APIContext apiContext() {
        String clientId = "Ac0FcC0Kwz4K5mkabTnhNb1IeCvasprhKhdI9Pp5oYRYomgYpK2J4op2R0Ts5P4jLlCa3boc7ZkJd6hU";
        String clientSecret = "EP4p5YnFbZJEfA14n5QdpYUnNWMbUeDOUq5gdhVf_0KYjdqCsDpOcsXGkZbKJe-imvl38qJzF731VPJi";
        APIContext apiContext = new APIContext(clientId, clientSecret, "sandbox");
        return apiContext;
    }
}
