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

    public Optional<Doctor> findDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return doctor;
    }

    public Doctor updateDoctor(Long id, Doctor doctor) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctor.getId());
        if (optionalDoctor.isPresent()) {
            Doctor existingDoctor = optionalDoctor.get();
            existingDoctor.setDoctorFirstname(doctor.getDoctorFirstname());
            existingDoctor.setDoctorLastname(doctor.getDoctorLastname());
            existingDoctor.setDoctorTelephone(doctor.getDoctorTelephone());
            existingDoctor.setDoctorSpeciality(doctor.getDoctorSpeciality());
            existingDoctor.setDoctorAbout(doctor.getDoctorAbout());
            doctorRepository.save(existingDoctor);
        }
        return doctor;
    }

    public void deleteDoctorById(Long id) {
        doctorRepository.deleteById(id);
    }

}










