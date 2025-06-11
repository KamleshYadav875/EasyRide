package com.easyride.easyRideApp.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    @ManyToOne
    private Ride ride;

    @ManyToOne
    private User fromUser;

    @ManyToOne
    private User toUser;

    private String comment;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
