package com.easyride.easyRideApp.services.impl;

import com.easyride.easyRideApp.entities.Payment;
import com.easyride.easyRideApp.entities.Ride;
import com.easyride.easyRideApp.entities.enums.PaymentStatus;
import com.easyride.easyRideApp.exceptions.ResourceNotFoundException;
import com.easyride.easyRideApp.repositories.PaymentRepository;
import com.easyride.easyRideApp.services.PaymentService;
import com.easyride.easyRideApp.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Override
    @Transactional
    public void processPayment(Ride ride) {

        Payment payment = paymentRepository.findByRide(ride).orElseThrow(() -> new ResourceNotFoundException("Payment not found with ride "+ride.getId()));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);

    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentStatus(PaymentStatus.PENDING)
                .amount(ride.getFare())
                .paymentMethod(ride.getPaymentMethod())
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}
