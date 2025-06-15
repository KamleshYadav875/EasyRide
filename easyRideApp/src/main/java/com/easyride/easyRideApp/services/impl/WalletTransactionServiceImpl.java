package com.easyride.easyRideApp.services.impl;

import com.easyride.easyRideApp.entities.WalletTransaction;
import com.easyride.easyRideApp.repositories.WalletTransactionRepository;
import com.easyride.easyRideApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {

        walletTransactionRepository.save(walletTransaction);
    }
}
