package com.healthcare.controller;


import com.healthcare.model.Billing;
import com.healthcare.service.BillingService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/billing")
public class BillingController {
    private final BillingService billingService;

    @RolesAllowed("ADMIN")
    @PostMapping("/create")
    public ResponseEntity<Void> createBilling(@RequestBody Billing billing) {
        billingService.saveBilling(billing);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBilling(@PathVariable Integer id) {
        billingService.deleteBilling(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RolesAllowed("ADMIN")
    @PutMapping("/update/{id}")
    public ResponseEntity<Billing> updateBilling(@PathVariable Integer id, @RequestBody Billing updatedBilling) {
        return ResponseEntity.ok(billingService.updatedBilling(updatedBilling, id));
    }


    @GetMapping("/doctor/{id}")
    public ResponseEntity<Billing> getBillingByUserId(@PathVariable Integer id) {
        try {
            Billing billing = billingService.getByDoctorId(id);
            return ResponseEntity.ok(billing);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/billing")
    public ResponseEntity<List<Billing>> findAllBilling() {
        return new ResponseEntity<>(billingService.findAllBilling(), HttpStatus.OK);
    }
}




