package com.easyride.easyRideApp.repositories;

import com.easyride.easyRideApp.entities.User;
import com.easyride.easyRideApp.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByUser(User user);
}
