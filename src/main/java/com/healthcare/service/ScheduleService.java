package com.healthcare.service;

import com.healthcare.model.Doctor;
import com.healthcare.model.Schedule;
import com.healthcare.model.enums.ScheduleAvailability;
import com.healthcare.model.records.DoctorRecord;
import com.healthcare.model.records.ScheduleResponse;
import com.healthcare.repository.DoctorRepository;
import com.healthcare.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;

    public void newSchedule(Schedule schedule) {
        try {
            scheduleRepository.save(schedule);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't save Schedule!");
        }
    }

    public void delete(Integer id) {
        try {
            scheduleRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't delete Schedule!");
        }
    }

    public void update(Integer id, Schedule update) {
        try {
            Schedule schedule = scheduleRepository.findById(id).orElseThrow();
            if (update.getScheduleDate() != null)
                schedule.setScheduleDate(update.getScheduleDate());
            if (update.getScheduleAvailability() != null)
                schedule.setScheduleAvailability(update.getScheduleAvailability());
            if (update.getDoctorId() != null)
                schedule.setDoctorId(update.getDoctorId());
            scheduleRepository.save(schedule);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't update Schedule!");
        }
    }

    public Schedule getById(Integer id) {
        try {
            return scheduleRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't save Schedule!");
        }
    }

    public List<ScheduleResponse> getAll() {
        return scheduleRepository.findAllByScheduleAvailabilityAndScheduleDateAfter(
                ScheduleAvailability.AVAILABLE, Instant.now())
                .stream().map(schedule -> {
                    Doctor doctor = doctorRepository.findById(schedule.getDoctorId()).orElse(null);
                    return ScheduleResponse.builder()
                            .scheduleId(schedule.getScheduleId())
                            .scheduleAvailability(schedule.getScheduleAvailability().name())
                            .scheduleDate(schedule.getScheduleDate())
                            .doctor(DoctorRecord.builder()
                                    .doctorId(doctor.getDoctorId())
                                    .name(doctor.getDoctorName())
                                    .speciality(doctor.getDoctorSpeciality().name())
                                    .build())
                            .build();
                }).toList();
    }

    public void save(Schedule schedule) {
        schedule.setScheduleAvailability(ScheduleAvailability.AVAILABLE);
        scheduleRepository.save(schedule);
    }
}
