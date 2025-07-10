package com.java.harrypotter.encyclopedia.external;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class HpApiExternal {

    private final WebClient webClient;

    public HpApiExternal() {
        this.webClient = WebClient.builder()
                .baseUrl("https://hp-api.onrender.com")
                .build();
    }

    public Object retrieve(String endpoint) {
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        //TODO passar para ResponseDTO e estudar a requisição.
    }

    public Object getAllCharacters() {
        return retrieve("/api/characters");
    }
}
