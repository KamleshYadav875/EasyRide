package com.easyride.easyRideApp.services.impl;

import com.easyride.easyRideApp.dto.DriverDto;
import com.easyride.easyRideApp.dto.RatingDto;
import com.easyride.easyRideApp.dto.RideDto;
import com.easyride.easyRideApp.entities.*;
import com.easyride.easyRideApp.entities.enums.RideRequestStatus;
import com.easyride.easyRideApp.entities.enums.RideStatus;
import com.easyride.easyRideApp.exceptions.ResourceNotFoundException;
import com.easyride.easyRideApp.repositories.DriverRepository;
import com.easyride.easyRideApp.services.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final RatingService ratingService;
    private final PaymentService paymentService;

    @Override
    public RideDto cancelRide(Long rideId) {

        Ride ride = rideService.findRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!ride.getRideStatus().equals(RideStatus.ACCEPTED)){
            throw new RuntimeException("RideRequest cannot be cancel, status is "+ride.getRideStatus());
        }

        if(!ride.getDriver().equals(driver)){
            throw new RuntimeException("You are not authorized to cancel the ride");
        }

        ride.setRideStatus(RideStatus.CANCELLED);
        return modelMapper.map(rideService.updateRide(ride), RideDto.class);
    }

    @Override
    @Transactional
    public RideDto startRide(Long rideId, String otp) {

        Ride ride = rideService.findRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!ride.getRideStatus().equals(RideStatus.ACCEPTED)){
            throw new RuntimeException("Ride cannot start in status: "+ride.getRideStatus());
        }

        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("You are not authorized to start the ride");
        }

        if(!otp.equals(ride.getOtp()))
        {
            throw new RuntimeException("Invalid Otp entered");
        }

        ride.setStartedAt(LocalDateTime.now());
        ride.setRideStatus(RideStatus.ON_TRIP);

        paymentService.createNewPayment(ride);

        return modelMapper.map(rideService.updateRide(ride), RideDto.class);

    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {
        Ride ride = rideService.findRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!ride.getRideStatus().equals(RideStatus.ON_TRIP))
        {
            throw new RuntimeException("Ride cannot end in status: "+ride.getRideStatus());
        }

        if(!driver.equals(ride.getDriver()))
        {
            throw new RuntimeException("You are not authorized to end the ride.");
        }

        ride.setRideStatus(RideStatus.COMPLETED);
        ride.setEndedAt(LocalDateTime.now());

        paymentService.processPayment(ride);
        return modelMapper.map(rideService.updateRide(ride), RideDto.class);
    }

    @Override
    public RatingDto rateRider(RatingDto ratingDto) {

        Ride ride = rideService.findRideById(ratingDto.getRide().getId());

        if(!ride.getRideStatus().equals(RideStatus.COMPLETED)){
            throw new RuntimeException("Rate rider once ride is completed");
        }

        Rating ratingRider = Rating.builder()
                .fromUser(ride.getDriver().getUser())
                .toUser(ride.getRider().getUser())
                .rating(ratingDto.getRating())
                .comment(ratingDto.getComment())
                .ride(ride)
                .build();

        return modelMapper.map(ratingService.submitRating(ratingRider), RatingDto.class);

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

        driver.setAvailable(false);
        driverRepository.save(driver);
        return modelMapper.map(ride, RideDto.class);

    }

    @Override
    public Driver getCurrentDriver() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return driverRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Driver is not associate with user"+user.getId()));
    }

    @Override
    public Driver createDriver(Driver driver) {
        return driverRepository.save(driver);
    }


}
