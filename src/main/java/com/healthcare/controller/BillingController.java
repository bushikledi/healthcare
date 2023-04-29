package com.healthcare.controller;


import com.healthcare.model.Billing;
import com.healthcare.model.User;
import com.healthcare.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/billing")
public class BillingController {
    BillingService billingService;

    BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createBilling(@RequestBody Billing billing) {
        billingService.saveBilling(billing);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBilling(@PathVariable Integer id) {
        billingService.deleteBilling(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Billing> updateBilling(@PathVariable Integer id, @RequestBody Billing updatedBilling) {
        return ResponseEntity.ok(billingService.updatedBilling(updatedBilling, id));
    }



    @GetMapping("/user/{userId}")
    public ResponseEntity<Billing> getBillingByUserId(@PathVariable Integer userId) {
        try {
            Billing billing = billingService.findBillingByUserId(userId);
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




