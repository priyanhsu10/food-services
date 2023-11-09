package com.foody.userservice.controllers;

import com.foody.userservice.dtos.UserDto;
import com.foody.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public Mono<String> heathCheck() {
        return Mono.just(LocalDateTime.now().toString());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDto>> getUserDetails(@RequestParam("id") String id) {
        return userService.getuserById(id).map(x -> ResponseEntity.ok().body(x));
    }

    @GetMapping("/all")
    public Flux<UserDto> getUserList() {
        return userService.getUsers();
    }
}
