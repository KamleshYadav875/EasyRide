package com.easyride.easyRideApp.services;

import com.easyride.easyRideApp.dto.RideRequestDto;
import com.easyride.easyRideApp.entities.Driver;
import com.easyride.easyRideApp.entities.Ride;
import com.easyride.easyRideApp.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long id);

    void matchWithDrivers(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequestDto rideRequestDto, Driver driver);

    Ride updateRideStatus(Long rideId, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRideOfDriver(Long driverId, PageRequest pageRequest);




}
