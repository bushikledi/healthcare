package com.healthcare.model;


import com.healthcare.model.enums.DoctorSpeciality;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @Column(name = "doctor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer doctorId;

    @Column(name = "doctor_name", nullable = false)
    private String doctorName;


    @Column(name = "doctor_speciality")
    @Enumerated(EnumType.STRING)
    private DoctorSpeciality doctorSpeciality;

    @Column(name = "doctor_about")
    private String doctorAbout;
}