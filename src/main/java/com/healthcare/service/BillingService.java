package com.healthcare.service;

import com.healthcare.model.Billing;
import com.healthcare.repository.BillingRepository;

import java.util.List;

public class BillingService {
    BillingRepository billingRepository;

    public BillingService(BillingRepository billingRepository) {
        this.billingRepository = billingRepository;
    }

    public Billing saveBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    public void deleteBilling(Integer id) {
        try {
            billingRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't delete user!\n" + e.getMessage());
        }
    }

    public Billing updatedBilling(Billing updatedBilling, Integer id) {
        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing not found!"));
        billing.setAmount(updatedBilling.getAmount());
        billing.setUserId(updatedBilling.getUserId());
        billingRepository.save(billing);
        return billing;
    }


    public Billing findBillingByUserId(Integer userId) {
        Billing billing = billingRepository.findByUserId(userId);
        if (billing == null) {
            throw new RuntimeException("Billing not found for user with ID " + userId);
        }
        return billing;
    }

    public List<Billing> findAllBilling() {
        return billingRepository.findAll();
    }


}