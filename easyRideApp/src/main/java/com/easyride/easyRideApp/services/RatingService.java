package com.easyride.easyRideApp.services;


import com.easyride.easyRideApp.dto.RatingDto;
import com.easyride.easyRideApp.entities.Rating;

public interface RatingService {

    Rating submitRating(Rating rating);
}
