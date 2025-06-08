package com.easyride.easyRideApp.services.impl;

import com.easyride.easyRideApp.dto.DriverDto;
import com.easyride.easyRideApp.dto.RideDto;
import com.easyride.easyRideApp.dto.RideRequestDto;
import com.easyride.easyRideApp.dto.RiderDto;
import com.easyride.easyRideApp.entities.RideRequest;
import com.easyride.easyRideApp.entities.Rider;
import com.easyride.easyRideApp.entities.User;
import com.easyride.easyRideApp.entities.enums.RideRequestStatus;
import com.easyride.easyRideApp.exceptions.ResourceNotFoundException;
import com.easyride.easyRideApp.repositories.RideRequestRepository;
import com.easyride.easyRideApp.repositories.RiderRepository;
import com.easyride.easyRideApp.services.RiderService;
import com.easyride.easyRideApp.strategies.DriverMatchingStrategy;
import com.easyride.easyRideApp.strategies.RideFareCalculationStrategy;
import com.easyride.easyRideApp.strategies.RideStrategyManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;

    private final RideRequestRepository rideRequestRepository;

    private final RiderRepository riderRepository;

    private final RideStrategyManager rideStrategyManager;


    // just to modify git
    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

        Rider rider  = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);

        double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest saveRideRequest = rideRequestRepository.save(rideRequest);

        rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);


        return modelMapper.map(saveRideRequest, RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        return null;
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
//        TODO : Implement once Spring security is implemented
        return riderRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Rider not found with id: "+1));


    }
}
