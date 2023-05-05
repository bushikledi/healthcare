package com.healthcare.service;

import com.healthcare.model.Billing;
import com.healthcare.repository.BillingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingService {
    private final BillingRepository billingRepository;

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
        billing.setDoctorId(updatedBilling.getDoctorId());
        billingRepository.save(billing);
        return billing;
    }


    public Billing getByDoctorId(Integer id) {
        return billingRepository.findByDoctorId(id)
                .orElseThrow(() -> new RuntimeException("Billing not found"));
    }

    public List<Billing> findAllBilling() {
        return billingRepository.findAll();
    }


}