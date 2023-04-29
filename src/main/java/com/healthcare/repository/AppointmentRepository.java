package com.healthcare.repository;

import com.healthcare.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    Appointment findByUserId(Integer userId);
}
