package com.foody.restorentservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantDto {
    @NotBlank(message = "Id is Required")
    private String id;
    private String name;
    private String address;
    private String city;
    @NotBlank(message = "owner id is Required")
    private String ownerId;
    private boolean isAvailable;

    public static RestaurantDto create(CreateRequest createRequest) {
        return RestaurantDto.builder()
                        .isAvailable(true)
                        .address(createRequest.getAddress())
                        .name(createRequest.getName())
                        .city(createRequest.getCity())
                        .ownerId(createRequest.getOwnerId())
                        .build();
    }
}

