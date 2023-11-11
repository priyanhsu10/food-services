package com.foody.userservice.controllers;

import com.foody.userservice.dtos.RegisterRequest;
import com.foody.userservice.dtos.UserDto;
import com.foody.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        return userService.getUserById(id).map(x -> ResponseEntity.ok().body(x));
    }

    @GetMapping("/all")
    public Flux<UserDto> getUserList() {
        return userService.getUsers();
    }
    @PostMapping("/register")
    public Mono<UserDto> createUser(@RequestBody RegisterRequest registerRequest){

         return userService.addUser(UserDto.create(registerRequest))
                .doOnNext(x->ResponseEntity.ok().body(x))
                .doOnError((e)->ResponseEntity.status(400).body(e.getLocalizedMessage()))
                 .log();

    }
}
