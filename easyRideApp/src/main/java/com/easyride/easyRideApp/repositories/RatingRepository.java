package com.easyride.easyRideApp.repositories;

import com.easyride.easyRideApp.entities.Rating;
import com.easyride.easyRideApp.entities.Ride;
import com.easyride.easyRideApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByRideAndFromUserAndToUser(Ride ride, User fromUser, User toUser);

    List<Rating> findByToUser(User toUser);
}
