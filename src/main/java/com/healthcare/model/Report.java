package com.healthcare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "reports")
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;

    @Column(name = "report_date")
    private Instant reportDate;

    @Column(name = "report_description")
    private String reportDescription;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "doctor_id")
    private Integer doctorId;


}
