package com.easyride.easyRideApp.services.impl;

import com.easyride.easyRideApp.dto.DriverDto;
import com.easyride.easyRideApp.dto.RideDto;
import com.easyride.easyRideApp.dto.RiderDto;
import com.easyride.easyRideApp.entities.Driver;
import com.easyride.easyRideApp.entities.Ride;
import com.easyride.easyRideApp.entities.RideRequest;
import com.easyride.easyRideApp.entities.enums.RideRequestStatus;
import com.easyride.easyRideApp.exceptions.ResourceNotFoundException;
import com.easyride.easyRideApp.repositories.DriverRepository;
import com.easyride.easyRideApp.services.DriverService;
import com.easyride.easyRideApp.services.RideRequestService;
import com.easyride.easyRideApp.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {

        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        Driver driver = getCurrentDriver();
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("RideRequest connot be accepted, status is "+rideRequest.getRideRequestStatus());
        }

        Ride ride = rideService.createNewRide(rideRequest, driver);
        return modelMapper.map(ride, RideDto.class);

    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L).orElseThrow(() -> new ResourceNotFoundException("Driver not found with id "+2));
    }
}
