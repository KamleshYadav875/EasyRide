package com.easyride.easyRideApp.strategies.impl;

import com.easyride.easyRideApp.dto.RideRequestDto;
import com.easyride.easyRideApp.entities.Driver;
import com.easyride.easyRideApp.entities.RideRequest;
import com.easyride.easyRideApp.repositories.DriverRepository;
import com.easyride.easyRideApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTENNearestDriver(rideRequest.getPickupLocation());
    }

}
