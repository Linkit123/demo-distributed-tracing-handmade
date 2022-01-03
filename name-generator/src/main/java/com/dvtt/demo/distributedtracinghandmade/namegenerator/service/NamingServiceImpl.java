package com.dvtt.demo.distributedtracinghandmade.namegenerator.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by linhtn on 1/2/2022.
 */
@Service
@AllArgsConstructor
public class NamingServiceImpl implements NamingService {

    private final RestTemplate restTemplate;

    public String naming() {
        var scientistUrl = "http://localhost:8069/api/v1/scientists/random";
        var animalUrl = "http://localhost:8096/api/v1/animals/random";

        String scientistName = this.httpGet(scientistUrl);
        String animalName = this.httpGet(animalUrl);
        return String.format("%s %s %s", scientistName, "loves", animalName);
    }

    private String httpGet(String url) {
        var uri = UriComponentsBuilder.fromHttpUrl(url)
                .build().toString();
        var headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT_LANGUAGE, "vi-VN");
        var exchange = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return exchange.getBody();
    }
}
