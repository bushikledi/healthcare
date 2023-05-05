package com.healthcare.controller;

import com.healthcare.model.Appointment;
import com.healthcare.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService AppointmentService;

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


    @PutMapping("/reschedule/{id}")
    public ResponseEntity<Appointment> rescheduleAppointment(@PathVariable Integer id, @RequestParam Integer scheduleId) {
        return ResponseEntity.ok(AppointmentService.updatedAppointment(scheduleId, id));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentByUserId(@PathVariable Integer id) {
        try {
            Appointment Appointment = AppointmentService.getById(id);
            return ResponseEntity.ok(Appointment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/all")
    public ResponseEntity<List<Appointment>> findAllAppointment() {
        return new ResponseEntity<>(AppointmentService.findAllAppointment(), HttpStatus.OK);
    }
}
