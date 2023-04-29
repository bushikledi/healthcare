package com.healthcare.service;

import com.healthcare.model.Report;
import com.healthcare.repository.ReportRepository;

import java.util.List;

public class ReportService {

    ReportRepository ReportRepository;

    public ReportService(ReportRepository ReportRepository){ this.ReportRepository=ReportRepository;}

    public Report saveReport(Report report) {
        return ReportRepository.save(report);
    }

    public void deleteReport(Integer id) {
        try {
            ReportRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't delete Report!\n" + e.getMessage());
        }
    }

    public Report updatedReport(Report updatedReport, Integer id) {
        Report report = ReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found!"));
        report.setUserId(updatedReport.getUserId());
        report.setReportDate(updatedReport.getReportDate());
        report.setReportDescription(updatedReport.getReportDescription());
        report.setDoctorId(updatedReport.getDoctorId());
        report.setReportTime(updatedReport.getReportTime());
        return report;
    }


    public Report findReportByUserId(Integer userId) {
        Report report = ReportRepository.findByUserId(userId);
        if (report == null) {
            throw new RuntimeException("Report not found for user with ID " + userId);
        }
        return report;
    }

    public List<Report> findAllReport() {
        return ReportRepository.findAll();
    }
}
