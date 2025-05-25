package com.easyride.easyRideApp.entities;

import com.easyride.easyRideApp.entities.enums.TransactionMethod;
import com.easyride.easyRideApp.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionMethod transactionMethod;

    @OneToOne
    private Ride ride;

    private  String transactionId;

    @CreationTimestamp
    private LocalDateTime createdDt;

    @ManyToOne
    private Wallet wallet;

}
