package com.easyride.easyRideApp.strategies.impl;

import com.easyride.easyRideApp.entities.Driver;
import com.easyride.easyRideApp.entities.Payment;
import com.easyride.easyRideApp.entities.enums.PaymentStatus;
import com.easyride.easyRideApp.entities.enums.TransactionMethod;
import com.easyride.easyRideApp.repositories.PaymentRepository;
import com.easyride.easyRideApp.services.WalletService;
import com.easyride.easyRideApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        double platformCommission = payment.getRide().getFare() * PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(),platformCommission,null,payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
