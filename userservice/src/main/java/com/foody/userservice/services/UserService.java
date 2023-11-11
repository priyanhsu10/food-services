package com.foody.userservice.services;

import com.foody.userservice.dtos.UserDto;
import com.foody.userservice.entites.User;
import com.foody.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;

@Service
public class UserService {


    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    private static UserDto userToUserDto(User x) {
        return new UserDto(x.getId(), x.getName(), x.getUserName(), x.getAddress(), null, x.getCurretLocation(), x.getRoles());
    }


    public Mono<UserDto> getUserById(String id) {

        return getById(id)
                .map(UserService::userToUserDto);

    }

    private Mono<User> getById(String id) {
        return repository.findById(id);
    }


    public Flux<UserDto> getUsers() {

        return repository.findAll()
                .map(UserService::userToUserDto);

    }


    public Mono<UserDto> addUser(UserDto userDto) {

        return repository.findByUserName(userDto.getUserName())
                .flatMap(user -> Mono.error(new RuntimeException("User already exists with ID: " + userDto.getUserName())))
                .switchIfEmpty(createUser(userDto))
                .map(x -> (UserDto) x)
                .log();

    }

    private Mono<UserDto> createUser(UserDto userDto) {

        var user = User.builder()
                .name(userDto.getName())
                .roles(userDto.getRoles())
                .password(userDto.getPassword())
                .userName(userDto.getUserName())
                .address(userDto.getAddress())
                .roles(userDto.getRoles())
                .build();
       return repository.save(user)
                .map(UserService::userToUserDto);


    }

    public Mono<UserDto> updateUser(UserDto userDto) {

        User user = User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .roles(userDto.getRoles())
                .password(userDto.getPassword())
                .address(userDto.getAddress())
                .roles(userDto.getRoles())
                .build();


        return getById(user.getId()).flatMap(u -> repository.save(user)
                .map(UserService::userToUserDto)).onErrorMap(x -> new RuntimeException("user Not found"));
    }
}
