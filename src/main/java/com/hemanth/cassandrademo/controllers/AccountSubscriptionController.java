package com.hemanth.cassandrademo.controllers;

import com.hemanth.cassandrademo.model.AccountSubscription;
import com.hemanth.cassandrademo.services.AccountSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountSubscriptionController {

    @Autowired
    private AccountSubscriptionService service;

    @PostMapping
    public ResponseEntity<AccountSubscription> createAccount(@RequestBody AccountSubscription accountSubscription) {
        service.save(accountSubscription);
        return ResponseEntity.ok(accountSubscription);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountSubscription> getAccountById(@PathVariable String id) {
        Optional<AccountSubscription> account = service.findById(id);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AccountSubscription>> getAllAccounts() {
        List<AccountSubscription> accounts = service.findAll();
        return ResponseEntity.ok(accounts);
    }
}
