package com.java.harrypotter.encyclopedia.external.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HpApiConfiguration {
    @Bean
    public static WebClient webClientBuilder() {
        return WebClient.builder()
                .baseUrl("https://hp-api.onrender.com/")
                .build();
    }
}
