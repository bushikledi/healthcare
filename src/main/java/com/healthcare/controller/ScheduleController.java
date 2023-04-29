package com.healthcare.controller;

import com.healthcare.model.Schedule;
import com.healthcare.model.records.ScheduleResponse;
import com.healthcare.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Schedule schedule) {
        scheduleService.update(id, schedule);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable Integer id) {
        return ResponseEntity.ok(scheduleService.getById(id));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAll());
    }
}
