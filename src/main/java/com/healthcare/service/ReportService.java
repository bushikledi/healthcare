package com.healthcare.service;

import com.healthcare.model.Report;
import com.healthcare.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository ReportRepository;

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
