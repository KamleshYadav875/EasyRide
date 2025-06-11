package com.easyride.easyRideApp.repositories;

import com.easyride.easyRideApp.entities.Rider;
import com.easyride.easyRideApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

    Optional<Rider> findByUser(User user);
}
