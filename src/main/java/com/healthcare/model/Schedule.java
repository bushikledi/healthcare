package com.healthcare.model;

import com.healthcare.model.enums.ScheduleAvailability;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@Entity
@Table(name = "schedules")
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Integer scheduleId;
    @Column(name = "schedule_date")
    private Instant scheduleDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "schedule_availability")
    private ScheduleAvailability scheduleAvailability;
    @Column(name = "doctor_id")
    private Integer doctorId;
}
