package com.foody.restorentservice.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String address;
    private String city;
    private String ownerId;
    private boolean isAvailable;
}
