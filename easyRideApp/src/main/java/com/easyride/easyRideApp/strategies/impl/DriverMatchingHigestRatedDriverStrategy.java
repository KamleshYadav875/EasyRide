package com.easyride.easyRideApp.strategies.impl;

import com.easyride.easyRideApp.dto.RideRequestDto;
import com.easyride.easyRideApp.entities.Driver;
import com.easyride.easyRideApp.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingHigestRatedDriverStrategy implements DriverMatchingStrategy {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
