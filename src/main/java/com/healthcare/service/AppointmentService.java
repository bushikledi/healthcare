package com.healthcare.service;

import com.healthcare.model.Appointment;
import com.healthcare.model.Billing;
import com.healthcare.repository.AppointmentRepository;

import java.util.List;

public class AppointmentService {
    AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository){ this.appointmentRepository=appointmentRepository;}

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Integer id) {
        try {
            appointmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't delete appointment!\n" + e.getMessage());
        }
    }

    public Appointment updatedAppointment(Appointment updatedAppointment, Integer id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("appointment not found!"));
        appointment.setDateTime(updatedAppointment.getDateTime());
        appointment.setUserId(updatedAppointment.getUserId());
        appointment.setDoctorId(updatedAppointment.getDoctorId());
        appointment.setBillingId(updatedAppointment.getBillingId());
        appointmentRepository.save(appointment);
        return appointment;
    }


    public Appointment findBillingByUserId(Integer userId) {
        Appointment appointment = appointmentRepository.findByUserId(userId);
        if (appointment == null) {
            throw new RuntimeException("appointment not found for user with ID " + userId);
        }
        return appointment;
    }

    public List<Appointment> findAllAppointment() {
        return appointmentRepository.findAll();
    }
}
