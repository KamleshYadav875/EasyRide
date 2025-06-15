package com.easyride.easyRideApp.services;

import com.easyride.easyRideApp.entities.Ride;
import com.easyride.easyRideApp.entities.User;
import com.easyride.easyRideApp.entities.Wallet;
import com.easyride.easyRideApp.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount,
                            String transactionId, Ride ride,
                            TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount,
                                 String transactionId, Ride ride,
                                 TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);
}
