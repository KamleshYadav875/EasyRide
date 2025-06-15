package com.easyride.easyRideApp.services;

import com.easyride.easyRideApp.dto.*;
import com.easyride.easyRideApp.entities.RideRequest;
import com.easyride.easyRideApp.entities.Rider;
import com.easyride.easyRideApp.entities.User;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideRequestDto cancelRideRequest(Long rideRequestId);

    RatingDto  rateDriver(RatingDto ratingDto);

    RiderDto getMyProfile();

    List<RideDto> getAllMyRides();

    Rider createRider(User user);

    Rider getCurrentRider();

}
