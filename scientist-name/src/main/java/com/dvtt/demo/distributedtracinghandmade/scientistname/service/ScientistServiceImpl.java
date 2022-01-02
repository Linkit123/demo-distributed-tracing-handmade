package com.dvtt.demo.distributedtracinghandmade.scientistname.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by linhtn on 1/2/2022.
 */
@Service
public class ScientistServiceImpl implements ScientistService {

    private final List<String> scientistNames;
    private final Random random;

    public ScientistServiceImpl() throws IOException {
        var inputStream = new ClassPathResource("static/scientists.txt").getInputStream();
        try (var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            scientistNames = reader.lines().collect(Collectors.toList());
        }
        this.random = new Random();
    }

    @Override
    public String getName() {
        return scientistNames.get(random.nextInt(scientistNames.size()));
    }
}
