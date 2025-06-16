package com.easyride.easyRideApp.services.impl;

import com.easyride.easyRideApp.dto.*;
import com.easyride.easyRideApp.entities.*;
import com.easyride.easyRideApp.entities.enums.RideRequestStatus;
import com.easyride.easyRideApp.entities.enums.RideStatus;
import com.easyride.easyRideApp.exceptions.ResourceNotFoundException;
import com.easyride.easyRideApp.exceptions.RunTimeConfilictException;
import com.easyride.easyRideApp.repositories.RideRequestRepository;
import com.easyride.easyRideApp.repositories.RiderRepository;
import com.easyride.easyRideApp.services.*;
import com.easyride.easyRideApp.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;

    private final RideRequestRepository rideRequestRepository;

    private final RiderRepository riderRepository;

    private final RideStrategyManager rideStrategyManager;

    private final RideRequestService rideRequestService;

    private final RideService rideService;

    private final RatingService ratingService;

    private final EmailSenderService emailSenderService;


    // just to modify git
    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

        Rider rider  = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest saveRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers = rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);
        String driverEmail [] = drivers.stream().map(d -> d.getUser().getEmail()).toArray(String[]::new);

        if(driverEmail.length > 0){
            emailSenderService.sendEmail(driverEmail, "New Ride Request Available Near You!", "Hello,\n" +
                    "\n" +
                    "A new ride request has been made near your current location.\n" +
                    "\n" +
                    "Please open the app to view the request and accept the ride if you're available.\n" +
                    "\n" +
                    "Quick actions lead to better earnings — don't miss out!\n" +
                    "\n" +
                    "Thank you,  \n" +
                    "Team EasyRide");
        }


        return modelMapper.map(saveRideRequest, RideRequestDto.class);
    }

    @Override
    public RideRequestDto cancelRideRequest(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);

        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RunTimeConfilictException("You cannot cancel riderequest in status: "+rideRequest.getRideRequestStatus());
        }

        rideRequest.setRideRequestStatus(RideRequestStatus.CANCELLED);
        return modelMapper.map(rideRequestService.update(rideRequest), RideRequestDto.class);
    }

    @Override
    public RatingDto rateDriver(RatingDto ratingDto) {
        Ride ride = rideService.findRideById(ratingDto.getRide().getId());

        if(!ride.getRideStatus().equals(RideStatus.COMPLETED)){
            throw new RuntimeException("Rate driver once ride is completed");
        }
        Rating ratingRider = Rating.builder()
                .fromUser(ride.getRider().getUser())
                .toUser(ride.getDriver().getUser())
                .rating(ratingDto.getRating())
                .comment(ratingDto.getComment())
                .ride(ride)
                .build();

        return modelMapper.map(ratingService.submitRating(ratingRider), RatingDto.class);
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();

        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return riderRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("Rider not associate with user "+user.getId()));
    }

}
