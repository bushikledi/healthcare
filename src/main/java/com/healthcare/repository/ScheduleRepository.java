package com.healthcare.repository;

import com.healthcare.model.Schedule;
import com.healthcare.model.enums.ScheduleAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findAllByScheduleAvailability(ScheduleAvailability scheduleAvailability);
}
