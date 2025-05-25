package com.easyride.easyRideApp.repositories;

import com.easyride.easyRideApp.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
