package com.dvtt.demo.distributedtracinghandmade.animalname.service;

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
public class AnimalServiceImpl implements AnimalService {

    private final List<String> animalNames;
    private final Random random;

    public AnimalServiceImpl() throws IOException {
        var inputStream = new ClassPathResource("static/animals.txt").getInputStream();
        try (var reader = new BufferedReader(new InputStreamReader(inputStream))) {
            animalNames = reader.lines().collect(Collectors.toList());
        }
        this.random = new Random();
    }

    @Override
    public String getName() {
        return animalNames.get(random.nextInt(animalNames.size()));
    }
}
