package com.foody.userservice.dtos;

import com.foody.userservice.entites.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String id;
    private String name;
    private String address;
    private String userName;
    private String password;
    private Location curretLocation;
    private List<String> roles;

    public static UserDto create(RegisterRequest registerRequest) {
        UserDto userDto = UserDto.builder().build();
        userDto.setName(registerRequest.getName());
        userDto.setUserName(registerRequest.getUserName());
        userDto.setPassword(registerRequest.getPassword());
        userDto.setAddress(registerRequest.getAddress());
        ArrayList<String> userRoles = new ArrayList<>();
        userRoles.add("AppUser");
        userDto.setRoles(userRoles);
        return userDto;
    }
}
