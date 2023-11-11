package com.foody.restorentservice.services;

import com.foody.restorentservice.dtos.RestaurantDto;
import com.foody.restorentservice.entites.Restaurant;
import com.foody.restorentservice.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    private static RestaurantDto entityToDto(Restaurant restaurant) {

        return RestaurantDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .ownerId(restaurant.getOwnerId())
                .city(restaurant.getCity())
                .address(restaurant.getAddress())
                .isAvailable(restaurant.isAvailable())
                .build();
    }

    private static Restaurant dtoToEntity(RestaurantDto restaurant) {

        return Restaurant.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .ownerId(restaurant.getOwnerId())
                .city(restaurant.getCity())
                .address(restaurant.getAddress())
                .isAvailable(restaurant.isAvailable())
                .build();
    }

    public Flux<RestaurantDto> getAll(String userId) {
        return restaurantRepository.findAllByOwnerId(userId)
                .map(RestaurantService::entityToDto);


    }

    public Mono<RestaurantDto> get(String id) {
        return restaurantRepository.findById(id)
                .map(RestaurantService::entityToDto);
    }

    public Mono<RestaurantDto> create(String id, RestaurantDto restaurantDto) {
        //todo:validate ownerId and context user id is match
        restaurantDto.setAvailable(true);
        //check restorent with same name and id is already present if  yes dont allow to create
        return restaurantRepository.findByNameAndOwnerId(id, restaurantDto.getOwnerId())
                .flatMap(x -> Mono.error(new RuntimeException(String.format("Restaurant Already added with name {} associated user {} ", restaurantDto.getName(), restaurantDto.getOwnerId()))))
                .switchIfEmpty(restaurantRepository.save(dtoToEntity(restaurantDto)).map(RestaurantService::entityToDto))
                .map(x->(RestaurantDto)x);

    }

    public Mono<RestaurantDto> update(RestaurantDto restaurantDto) {
        //todo:validate ownerId and context user id is match
        //check restorent with same name and id is already present if  yes dont allow to create
        return restaurantRepository.findByNameAndOwnerId(restaurantDto.getName(), restaurantDto.getOwnerId())
                .flatMap(x -> restaurantRepository.save(dtoToEntity(restaurantDto)))
                .switchIfEmpty(Mono.error(new RuntimeException("Restaurant Not resent with name "+restaurantDto.getName()+" associated user  "+restaurantDto.getOwnerId())))
                .map(RestaurantService::entityToDto);

    }
}
