package com.healthcare.service;

import com.healthcare.model.Doctor;
import com.healthcare.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor findDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public Doctor saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return doctor;
    }

    public Doctor updateDoctor(Long id, Doctor updateDoctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not Found"));
        if (!updateDoctor.getDoctorFirstname().isBlank())
            existingDoctor.setDoctorFirstname(updateDoctor.getDoctorFirstname());
        if (!updateDoctor.getDoctorLastname().isBlank())
            existingDoctor.setDoctorLastname(updateDoctor.getDoctorLastname());
        if (!updateDoctor.getDoctorTelephone().isBlank())
            existingDoctor.setDoctorTelephone(updateDoctor.getDoctorTelephone());
        if (updateDoctor.getDoctorSpeciality() != null)
            existingDoctor.setDoctorSpeciality(updateDoctor.getDoctorSpeciality());
        if (updateDoctor.getDoctorAbout().isBlank())
            existingDoctor.setDoctorAbout(updateDoctor.getDoctorAbout());
        doctorRepository.save(existingDoctor);
        return existingDoctor;
    }

    public void deleteDoctorById(Long id) {
        doctorRepository.deleteById(id);
    }

}










