package com.easyride.easyRideApp.repositories;

import com.easyride.easyRideApp.entities.Payment;
import com.easyride.easyRideApp.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
