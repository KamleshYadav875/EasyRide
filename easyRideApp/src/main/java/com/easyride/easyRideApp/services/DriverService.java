package com.easyride.easyRideApp.services;

import com.easyride.easyRideApp.dto.DriverDto;
import com.easyride.easyRideApp.dto.RideDto;
import com.easyride.easyRideApp.dto.RiderDto;
import com.easyride.easyRideApp.entities.Driver;

import java.util.List;

public interface DriverService {

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRides();

    RideDto acceptRide(Long rideRequestId);

    Driver getCurrentDriver();
}
