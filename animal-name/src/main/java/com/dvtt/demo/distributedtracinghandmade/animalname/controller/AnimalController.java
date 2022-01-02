package com.dvtt.demo.distributedtracinghandmade.animalname.controller;

import com.dvtt.demo.distributedtracinghandmade.animalname.service.AnimalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by linhtn on 1/2/2022.
 */
@RequestMapping("/api/v1/animals")
@RestController
@AllArgsConstructor
public class AnimalController {
    private final AnimalService service;

    @GetMapping("/random")
    public String getName() {
        return service.getName();
    }
}
