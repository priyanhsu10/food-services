package com.foody.userservice.dtos;

import com.foody.userservice.entites.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private  String name;
    private String password;
}
