package com.healthcare.controller;

import com.healthcare.model.Report;
import com.healthcare.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ReportController {
    com.healthcare.service.ReportService ReportService;

    ReportController(ReportService ReportService) {
        this.ReportService = ReportService;
    }

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
    @GetMapping("/Report")
    public ResponseEntity<List<Report>> findAllReport() {
        return new ResponseEntity<>(ReportService.findAllReport(), HttpStatus.OK);
    }
}
