package com.healthcare.controller;

import com.healthcare.model.Doctor;
import com.healthcare.model.records.DoctorRequest;
import com.healthcare.service.DoctorService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(doctorService.findDoctorById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/add")
    public ResponseEntity<Doctor> addDoctor(@RequestBody DoctorRequest doctor) {
        doctorService.saveDoctor(doctor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RolesAllowed("ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateDoctor(@PathVariable Integer id, @RequestBody Doctor doctor) {
        try {
            doctorService.updateDoctor(id, doctor);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable Integer id) {
        try {
            doctorService.deleteDoctorById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
