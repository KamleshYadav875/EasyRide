package com.easyride.easyRideApp.strategies.impl;

import com.easyride.easyRideApp.dto.RideRequestDto;
import com.easyride.easyRideApp.strategies.RideFareCalculationStrategy;
import org.springframework.stereotype.Service;

@Service
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
