package com.healthcare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="report_time")
    private Time reportTime;

    @Column(name="report_date")
    private LocalDate reportDate;

    @Column(name="report_description")
    private String reportDescription;

    @Column(name="user_id")
    private int userId;

    @Column(name="doctor_id")
    private int doctorId;


}
