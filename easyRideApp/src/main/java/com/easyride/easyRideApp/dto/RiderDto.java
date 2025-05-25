package com.easyride.easyRideApp.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RiderDto {

    private UserDto user;
    private Double rating;
}
