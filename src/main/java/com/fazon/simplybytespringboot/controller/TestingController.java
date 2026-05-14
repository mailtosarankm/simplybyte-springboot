package com.fazon.simplybytespringboot.controller;

import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health")
@AllArgsConstructor
public class TestingController {

    @GetMapping("/")
    public String health() {
    return "Hello Everyone, Pipeline change pannitom";
    }
    @GetMapping("/add")
    public int add() {
        return 2 + 3;
    }

}
