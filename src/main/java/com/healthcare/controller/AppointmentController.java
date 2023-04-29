package com.healthcare.controller;

import com.healthcare.model.Appointment;
import com.healthcare.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AppointmentController {
    AppointmentService AppointmentService;

    AppointmentController(AppointmentService AppointmentService) {
        this.AppointmentService = AppointmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createAppointment(@RequestBody Appointment Appointment) {
        AppointmentService.saveAppointment(Appointment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id) {
        AppointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Integer id, @RequestBody Appointment updatedAppointment) {
        return ResponseEntity.ok(AppointmentService.updatedAppointment(updatedAppointment, id));
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<Appointment> getAppointmentByUserId(@PathVariable Integer userId) {
        try {
            Appointment Appointment = AppointmentService.findAppointmentByUserId(userId);
            return ResponseEntity.ok(Appointment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/Appointment")
    public ResponseEntity<List<Appointment>> findAllAppointment() {
        return new ResponseEntity<>(AppointmentService.findAllAppointment(), HttpStatus.OK);
    }
}
