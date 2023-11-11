package com.foody.restorentservice.controllers;

import com.foody.restorentservice.dtos.CreateRequest;
import com.foody.restorentservice.dtos.RestaurantDto;
import com.foody.restorentservice.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("v1/restourant")
public class RestaurantController {
private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RequestMapping("/health")
    public Mono<String> heathCheck() {
        return Mono.just(LocalDateTime.now().toString());
    }

    @RequestMapping("/all/{userid}")
    public Flux<RestaurantDto> getAll(@PathVariable String userid){

        return restaurantService.getAll(userid);
    }


    @RequestMapping("/{id}")
    public Mono<RestaurantDto> get(@PathVariable String id){

        return restaurantService.get(id);
    }
    @PostMapping("/{ownerId}")
    public Mono<ResponseEntity<RestaurantDto>> create(@Valid @RequestBody CreateRequest createRequest, @PathVariable String ownerId){

        return restaurantService.create(ownerId,RestaurantDto.create(createRequest))
                .map(x-> ResponseEntity.ok().body(x))
                .doOnError(x->ResponseEntity.badRequest().body(x.getLocalizedMessage()));
    }
    @PutMapping("/{id}")
    public Mono<ResponseEntity<RestaurantDto>> get(@Valid @RequestBody RestaurantDto restaurantDto, @PathVariable String id){

        return restaurantService.update(restaurantDto)
                .map(x-> ResponseEntity.ok().body(x))
                .doOnError(x->ResponseEntity.badRequest().body(x.getLocalizedMessage()));
    }
}
