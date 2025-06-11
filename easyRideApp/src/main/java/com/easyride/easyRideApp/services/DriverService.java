package com.easyride.easyRideApp.services;

import com.easyride.easyRideApp.dto.DriverDto;
import com.easyride.easyRideApp.dto.RatingDto;
import com.easyride.easyRideApp.dto.RideDto;
import com.easyride.easyRideApp.entities.Driver;
import com.easyride.easyRideApp.entities.User;

import java.util.List;

public interface DriverService {

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RatingDto rateRider(RatingDto ratingDto);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRides();

    RideDto acceptRide(Long rideRequestId);

    Driver getCurrentDriver();



}
