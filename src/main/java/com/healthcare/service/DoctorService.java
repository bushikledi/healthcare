package com.healthcare.service;

import com.healthcare.model.Doctor;
import com.healthcare.model.User;
import com.healthcare.model.enums.Role;
import com.healthcare.repository.DoctorRepository;
import com.healthcare.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AuthenticationService authenticationService;


    public void save(Doctor doctor) {
        try {

            doctorRepository.save(doctor);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't save user!\n" + e.getMessage());
        }
    }
    public Doctor updateDoctor(Doctor doctor) {
        return doctor;
    }


    public void update(Doctor doctorUpdate) {
        try {
            Doctor doctor = authenticationService.getDoctor();
            doctor.setDoctorFirstname(doctorUpdate.getDoctorFirstname());
            doctor.setDoctorLastname(doctorUpdate.getDoctorLastname());
            doctor.setDoctorTelephone(doctorUpdate.getDoctorTelephone());
            doctor.setDoctorSpeciality(doctorUpdate.getDoctorSpeciality());
            doctor.setDoctorAbout(doctorUpdate.getDoctorAbout());
            doctorRepository.save(doctor);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't update doctor!\n" + e.getMessage());
        }




    }


    public Doctor getDoctor() {

        return authenticationService.getDoctor();
    }

    public void delete() {
        try {
            doctorRepository.delete(
                    authenticationService.getDoctor()
            );
        } catch (Exception e) {
            throw new RuntimeException("Couldn't delete doctor!\n" + e.getMessage());
        }
    }
    }



