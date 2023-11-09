package com.foody.userservice.dtos;

import com.foody.userservice.entites.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String id;
    private  String name;
    private String address;
    private String password;
    private Location curretLocation;
    private List<String> roles;
}
