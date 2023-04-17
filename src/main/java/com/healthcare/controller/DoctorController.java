package com.healthcare.controller;
import com.healthcare.model.User;
import com.healthcare.service.DoctorService;

import com.healthcare.model.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorService doctorService;



    @GetMapping("/all")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Doctor> getDoctor() {
        return ResponseEntity.ok(doctorService.getDoctor());
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> register(@Valid @RequestBody Doctor doctor) {
        doctorService.save(doctor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @PutMapping("/update")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> update(@RequestBody Doctor doctor) {
        doctorService.update(doctor);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Void> delete() {
        doctorService.delete();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
