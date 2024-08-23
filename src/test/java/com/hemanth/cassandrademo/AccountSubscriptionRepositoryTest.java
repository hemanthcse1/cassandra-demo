package com.hemanth.cassandrademo;

import com.datastax.oss.driver.api.core.CqlSession;
import com.hemanth.cassandrademo.model.AccountSubscription;
import com.hemanth.cassandrademo.repositories.AccountSubscriptionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AccountSubscriptionRepositoryTest {

    @Autowired
    private AccountSubscriptionRepository repository;

    private String accountId;

    @BeforeEach
    public void setUp() {
        accountId = "12345";
    }

    @AfterEach
    public void tearDown() {
        repository.deleteById(accountId);
    }

    @Test
    public void testSaveAndFindById() {
        AccountSubscription accountSubscription = new AccountSubscription();
        accountSubscription.setAccountId(accountId);
        accountSubscription.setAccountType("Premium");
        accountSubscription.setStatus("Active");
        accountSubscription.setCreatedAt(new Date().toInstant());
        accountSubscription.setUpdatedAt(new Date().toInstant());

        repository.save(accountSubscription);

        Optional<AccountSubscription> foundSubscription = repository.findById(accountId);
        Assertions.assertNotNull(foundSubscription);
        Assertions.assertTrue(foundSubscription.isPresent(), "AccountSubscription should be present");
        Assertions.assertEquals("Premium", foundSubscription.get().getAccountType());

    }

    @Test
    public void testDeleteById() {
        AccountSubscription accountSubscription = new AccountSubscription();
        accountSubscription.setAccountId(accountId);
        accountSubscription.setAccountType("Premium");
        accountSubscription.setStatus("Active");
        accountSubscription.setCreatedAt(new Date().toInstant());
        accountSubscription.setUpdatedAt(new Date().toInstant());

        repository.save(accountSubscription);
        repository.deleteById(accountId);

        Optional<AccountSubscription> foundSubscription = repository.findById(accountId);
        Assertions.assertNotNull(foundSubscription);
    }
}
