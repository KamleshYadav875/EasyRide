package com.easyride.easyRideApp.strategies.impl;

import com.easyride.easyRideApp.entities.Driver;
import com.easyride.easyRideApp.entities.Payment;
import com.easyride.easyRideApp.entities.Rider;
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
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);

        double driverCut = payment.getRide().getFare() * (1 - PLATFORM_COMMISSION);
        walletService.addMoneyToWallet(driver.getUser(), driverCut, null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
