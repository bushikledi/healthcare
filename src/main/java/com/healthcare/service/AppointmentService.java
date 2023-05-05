package com.healthcare.service;

import com.healthcare.model.Appointment;
import com.healthcare.model.Billing;
import com.healthcare.model.Schedule;
import com.healthcare.model.enums.AppointmentStatus;
import com.healthcare.model.enums.ScheduleAvailability;
import com.healthcare.repository.AppointmentRepository;
import com.healthcare.repository.BillingRepository;
import com.healthcare.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AuthenticationService authenticationService;
    private final ScheduleRepository scheduleRepository;
    private final BillingRepository billingRepository;

    public void saveAppointment(Appointment appointment) {
        Schedule schedule = scheduleRepository.findByScheduleDateAndDoctorId(
                appointment.getAppointmentDate(), appointment.getDoctorId()
        ).orElseThrow();
        schedule.setScheduleAvailability(ScheduleAvailability.NOT_AVAILABLE);
        scheduleRepository.save(schedule);
        appointment.setAppointmentStatus(AppointmentStatus.RESERVED);
        appointment.setUserId(authenticationService.getUser().getUserId());
        appointmentRepository.save(appointment);
    }

    @Transactional
    public void deleteAppointment(Integer id) {
        try {
            Appointment appointment = appointmentRepository.findById(id).orElseThrow();
            Schedule schedule = scheduleRepository.findByScheduleDateAndDoctorId(
                    appointment.getAppointmentDate(), appointment.getDoctorId()
            ).orElseThrow();
            schedule.setScheduleAvailability(ScheduleAvailability.AVAILABLE);
            scheduleRepository.save(schedule);
            appointmentRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't delete appointment!\n" + e.getMessage());
        }
    }

    public Appointment updatedAppointment(Integer scheduleId, Integer id) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
        Appointment appointment = appointmentRepository.findById(id).orElseThrow();
        Billing billing = billingRepository.findByDoctorId(schedule.getDoctorId()).orElseThrow();
        Schedule oldSchedule = scheduleRepository.findByScheduleDateAndDoctorId(
                appointment.getAppointmentDate(), appointment.getDoctorId()
        ).orElseThrow();

        appointment.setAppointmentStatus(AppointmentStatus.RESCHEDULED);
        appointment.setAppointmentDate(schedule.getScheduleDate());
        appointment.setDoctorId(schedule.getDoctorId());
        appointment.setBillingId(billing.getBillingId());
        appointmentRepository.save(appointment);

        oldSchedule.setScheduleAvailability(ScheduleAvailability.AVAILABLE);
        scheduleRepository.save(oldSchedule);

        schedule.setScheduleAvailability(ScheduleAvailability.NOT_AVAILABLE);
        scheduleRepository.save(schedule);

        return appointment;
    }


    public Appointment getById(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public List<Appointment> findAllAppointment() {
        return appointmentRepository.findAllByUserId(
                authenticationService.getUser().getUserId()
        );
    }


}
