package com.foody.userservice.repositories;

import com.foody.userservice.entites.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserRepository extends ReactiveMongoRepository<User,String> {

    Mono<User> findByUserName(String userName);
}
