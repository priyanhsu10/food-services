package com.foody.restorentservice.repositories;

import com.foody.restorentservice.entites.Restaurant;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RestaurantRepository extends ReactiveMongoRepository<Restaurant,String> {
    Flux<Restaurant> findAllByOwnerId(String userId);

    Mono<Restaurant> findByNameAndOwnerId(String name, String ownerId);
}
