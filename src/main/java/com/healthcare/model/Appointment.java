package com.healthcare.model;

import com.healthcare.model.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "appointments")
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;

    @Column(name = "appointment_date")
    private Instant appointmentDate;

    @Column(name = "appointment_status")
    @Enumerated(value = EnumType.STRING)
    private AppointmentStatus appointmentStatus;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "billing_id")
    private Integer billingId;


}
