package com.healthcare.repository;

import com.healthcare.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingRepository extends JpaRepository<Billing,Integer> {

    Billing findByUserId(Integer userId);
}