package com.foody.userservice.dtos;

import com.foody.userservice.entites.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private  String name;
    private  String userName;
    private String address;
    private String password;
    private Location curretLocation;
}
