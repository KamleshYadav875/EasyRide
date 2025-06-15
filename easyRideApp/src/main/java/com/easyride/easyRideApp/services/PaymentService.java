package com.easyride.easyRideApp.services;

import com.easyride.easyRideApp.entities.Payment;
import com.easyride.easyRideApp.entities.Ride;
import com.easyride.easyRideApp.entities.enums.PaymentStatus;

public interface PaymentService {
    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
