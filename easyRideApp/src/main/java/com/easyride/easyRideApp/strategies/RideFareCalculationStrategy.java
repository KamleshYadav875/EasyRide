package com.easyride.easyRideApp.strategies;

import com.easyride.easyRideApp.dto.RideRequestDto;

public interface RideFareCalculationStrategy {

    double calculateFare(RideRequestDto rideRequestDto);
}
