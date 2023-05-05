package com.healthcare.controller;

import com.healthcare.model.Report;
import com.healthcare.service.ReportService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService ReportService;

    @RolesAllowed("ADMIN")
    @PostMapping("/create")
    public ResponseEntity<Void> createReport(@RequestBody Report report) {
        ReportService.saveReport(report);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Integer id) {
        ReportService.deleteReport(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Integer id, @RequestBody Report updatedReport) {
        return ResponseEntity.ok(ReportService.updatedReport(updatedReport, id));
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<Report> getReportByUserId(@PathVariable Integer userId) {
        try {
            Report report = ReportService.findReportByUserId(userId);
            return ResponseEntity.ok(report);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/reports")
    public ResponseEntity<List<Report>> findAllReport() {
        return new ResponseEntity<>(ReportService.findAllReport(), HttpStatus.OK);
    }
}
