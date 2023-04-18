package com.healthcare.controller;
import com.healthcare.repository.DoctorRepository;
import com.healthcare.service.DoctorService;

import com.healthcare.model.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;


    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.findAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorService.findDoctorById(id);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }




    @PostMapping("/add")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        doctorService.saveDoctor(doctor);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }



    public void updateDoctor(Doctor doctor) {
        DoctorRepository doctorRepository = null;
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
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable Long id) {
        Optional<Doctor> doctor = doctorService.findDoctorById(id);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        doctorService.deleteDoctorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
