package com.easyride.easyRideApp.services;

import com.easyride.easyRideApp.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
