package com.hemanth.cassandrademo.model;

import java.sql.Timestamp;
import java.time.Instant;

public class AccountSubscription {

    private String accountId;
    private String accountType;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;

    public AccountSubscription(String accountId, String accountType, String status, Instant createdAt, Instant updatedAt) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public AccountSubscription() {

    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
