package com.healthcare.repository;

import com.healthcare.model.Schedule;
import com.healthcare.model.enums.ScheduleAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByScheduleAvailabilityAndScheduleDateAfter(ScheduleAvailability scheduleAvailability, Instant scheduleDate);

    Optional<Schedule> findByScheduleDateAndDoctorId(Instant scheduleDate, Integer doctorId);
}
