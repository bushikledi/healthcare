package com.healthcare.service;

import com.healthcare.model.Billing;
import com.healthcare.model.Doctor;
import com.healthcare.model.records.DoctorRequest;
import com.healthcare.repository.BillingRepository;
import com.healthcare.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final BillingRepository billingRepository;

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor findDoctorById(Integer id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public void saveDoctor(DoctorRequest doctor) {
        doctorRepository.save(Doctor.builder()
                        .doctorAbout(doctor.doctorAbout())
                        .doctorSpeciality(doctor.doctorSpeciality())
                        .doctorName(doctor.doctorName())
                .build());
        Doctor doctor1 = doctorRepository.findByDoctorName(doctor.doctorName()).orElseThrow();
        billingRepository.save(Billing.builder()
                        .amount(doctor.amount())
                        .doctorId(doctor1.getDoctorId())
                .build());
    }

    public void updateDoctor(Integer id, Doctor updateDoctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not Found"));
        if (!updateDoctor.getDoctorName().isBlank())
            existingDoctor.setDoctorName(updateDoctor.getDoctorName());
        if (updateDoctor.getDoctorSpeciality() != null)
            existingDoctor.setDoctorSpeciality(updateDoctor.getDoctorSpeciality());
        if (updateDoctor.getDoctorAbout().isBlank())
            existingDoctor.setDoctorAbout(updateDoctor.getDoctorAbout());
        doctorRepository.save(existingDoctor);
    }

    public void deleteDoctorById(Integer id) {
        doctorRepository.deleteById(id);
    }

}










