package com.healthcare.controller;

import com.healthcare.model.Schedule;
import com.healthcare.model.records.ScheduleResponse;
import com.healthcare.service.ScheduleService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @RolesAllowed("ADMIN")
    @PostMapping("/create")
    public ResponseEntity<Void> delete(@RequestBody Schedule schedule) {
        scheduleService.save(schedule);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Schedule schedule) {
        scheduleService.update(id, schedule);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getSchedule(@PathVariable Integer id) {
        return ResponseEntity.ok(scheduleService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        List<ScheduleResponse> list = scheduleService.getAll();
        log.info(list.toString());
        return ResponseEntity.ok(list);
    }
}
