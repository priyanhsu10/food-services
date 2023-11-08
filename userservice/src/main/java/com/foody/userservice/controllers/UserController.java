package com.foody.userservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @GetMapping()
    public Mono<String> heathCheck() {
        return Mono.just(LocalDateTime.now().toString());
    }
}
