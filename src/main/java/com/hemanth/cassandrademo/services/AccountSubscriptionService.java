package com.hemanth.cassandrademo.services;

import com.hemanth.cassandrademo.model.AccountSubscription;
import com.hemanth.cassandrademo.repositories.AccountSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountSubscriptionService {

    @Autowired
    private AccountSubscriptionRepository repository;

    public void save(AccountSubscription accountSubscription) {
        repository.save(accountSubscription);
    }

    public Optional<AccountSubscription> findById(String accountId) {
        return repository.findById(accountId);
    }

    public void deleteById(String accountId) {
        repository.deleteById(accountId);
    }

    public List<AccountSubscription> findAll() {
        return repository.findAll();
    }
}
