package com.easyride.easyRideApp.strategies;

import com.easyride.easyRideApp.dto.RideRequestDto;
import com.easyride.easyRideApp.entities.RideRequest;

public interface RideFareCalculationStrategy {

    static final double DEFAULT_FARE_RATE = 10;
    static final double BASE_FARE = 20;

    double calculateFare(RideRequest rideRequest);
}
