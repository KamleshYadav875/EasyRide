package com.easyride.easyRideApp.services.impl;

import com.easyride.easyRideApp.entities.*;
import com.easyride.easyRideApp.entities.enums.Role;
import com.easyride.easyRideApp.exceptions.ResourceNotFoundException;
import com.easyride.easyRideApp.repositories.DriverRepository;
import com.easyride.easyRideApp.repositories.RatingRepository;
import com.easyride.easyRideApp.repositories.RiderRepository;
import com.easyride.easyRideApp.services.RatingService;
import com.easyride.easyRideApp.services.RideService;
import com.easyride.easyRideApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RideService rideService;
    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;

    @Override
    public Rating submitRating(Rating rating) {

        Integer score = rating.getRating();
        Ride ride = rideService.findRideById(rating.getRide().getId());
        User fromUser = rating.getFromUser();
        User toUser = rating.getToUser();

        if(score < 1 || score > 5){
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        ratingRepository.findByRideAndFromUserAndToUser(ride, fromUser, toUser).ifPresent(existing -> {
            throw new IllegalStateException("Rating already submitted for this ride and user");
        });

        Rating savedRating = ratingRepository.save(rating);

        updateUserAverageRating(toUser, score);

        return savedRating;

    }

    private void updateUserAverageRating(User toUser, Integer score) {

        List<Rating> ratings = ratingRepository.findByToUser(toUser);
        double avg = ratings.stream().mapToInt(Rating::getRating).average().orElse(score);

        if (toUser.getRoles().contains(Role.DRIVER)) {
            Driver driver = driverRepository.findByUser(toUser).orElseThrow(() -> new ResourceNotFoundException("Driver not found with user id: "+toUser.getId()));
            driver.setRating(avg);
            driverRepository.save(driver);
        } else if (toUser.getRoles().contains(Role.RIDER)) {
            Rider rider = riderRepository.findByUser(toUser).orElseThrow(() -> new ResourceNotFoundException("Driver not found with user id: "+toUser.getId()));;
            rider.setRating(avg);
            riderRepository.save(rider);
        }
    }

}
