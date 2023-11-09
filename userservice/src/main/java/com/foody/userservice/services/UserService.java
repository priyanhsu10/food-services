package com.foody.userservice.services;

import com.foody.userservice.dtos.UserDto;
import com.foody.userservice.entites.User;
import com.foody.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {


    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Mono<UserDto> getuserById(String id) {

        return repository.findById(id)
                .map(x -> new UserDto(x.getId(), x.getName(), x.getAddress(), null, x.getCurretLocation(), x.getRoles()));

    }

    public Flux<UserDto> getUsers() {

        return repository.findAll()
                .map(x -> new UserDto(x.getId(), x.getName(), x.getAddress(), null, x.getCurretLocation(), x.getRoles()));

    }


    public Mono<UserDto> addUser(UserDto userDto) {

        User user= User.builder()
                .name(userDto.getName())
                .roles(userDto.getRoles())
                .password(userDto.getPassword())
                .address(userDto.getAddress())
                .roles(userDto.getRoles())
                .build();
        return repository.save(user)
                .map(x -> new UserDto(x.getId(), x.getName(), x.getAddress(), null, x.getCurretLocation(), x.getRoles()));

    }
    public Mono<UserDto> updateUser(UserDto userDto) {

        User user= User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .roles(userDto.getRoles())
                .password(userDto.getPassword())
                .address(userDto.getAddress())
                .roles(userDto.getRoles())
                .build();
        return repository.save(user)
                .map(x -> new UserDto(x.getId(), x.getName(), x.getAddress(), null, x.getCurretLocation(), x.getRoles()));

    }
}
