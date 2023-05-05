package com.healthcare.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "billings")
@AllArgsConstructor
@NoArgsConstructor
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billingId;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "amount")
    private double amount;
}
