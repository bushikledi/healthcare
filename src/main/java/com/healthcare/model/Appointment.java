package com.healthcare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="date_time")
    private LocalDate dateTime;

    @Column(name="status_id")
    private String statusId;

    @Column(name="user_id")
    private int userId;

    @Column(name="doctor_id")
    private int doctorId;

    @Column(name="billing_id")
    private int billingId;


}
