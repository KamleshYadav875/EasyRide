package com.easyride.easyRideApp.strategies;

import com.easyride.easyRideApp.entities.Payment;

public interface PaymentStrategy {

    Double PLATFORM_COMMISSION = 0.3;
    void processPayment(Payment payment);
}
