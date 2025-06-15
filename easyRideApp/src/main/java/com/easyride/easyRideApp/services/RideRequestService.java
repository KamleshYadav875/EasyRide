package com.easyride.easyRideApp.services;

import com.easyride.easyRideApp.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    RideRequest update(RideRequest rideRequest);
}
