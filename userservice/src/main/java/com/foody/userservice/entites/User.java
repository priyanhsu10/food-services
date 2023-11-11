package com.foody.userservice.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class User {
    @Id
    private String id;
    private  String name;
    private String userName;
    private String address;
    private String password;
    private Location curretLocation;
    private List<String> roles;
    public  void init(){
        roles= new ArrayList<>();
        roles.add("consumer");
    }
}
