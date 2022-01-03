package com.dvtt.demo.distributedtracinghandmade.scientistname.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by linhtn on 1/2/2022.
 */
@Service
@AllArgsConstructor
public class ScientistServiceImpl implements ScientistService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public String getName() {
        var random = new Random();
        var url = "https://61d2c38bb4c10c001712b58a.mockapi.io/api/v1/scientists";
        var s = httpGet(url);
        try {
            var listScientist = objectMapper.readValue(s, new TypeReference<List<HashMap<String, String>>>() {
            });
            var scientistNames = listScientist.stream().map(x -> x.get("name")).collect(Collectors.toList());
            return scientistNames.get(random.nextInt(scientistNames.size()));
        } catch (Exception ex) {
            return "";
        }

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
