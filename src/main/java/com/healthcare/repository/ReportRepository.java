package com.healthcare.repository;

import com.healthcare.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report,Integer> {
    Report findByUserId(Integer userId);
}
