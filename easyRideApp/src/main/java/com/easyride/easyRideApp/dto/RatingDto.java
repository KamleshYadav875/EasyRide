package com.easyride.easyRideApp.dto;

import com.easyride.easyRideApp.entities.Ride;
import com.easyride.easyRideApp.entities.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RatingDto {

    private Long id;

    private Integer rating;

    private Ride ride;

    private UserDto fromUser;

    private UserDto toUser;

    private String comment;

    private LocalDateTime createdAt;

}
