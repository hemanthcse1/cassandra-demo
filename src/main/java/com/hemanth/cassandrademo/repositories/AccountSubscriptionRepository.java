package com.hemanth.cassandrademo.repositories;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.hemanth.cassandrademo.model.AccountSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountSubscriptionRepository {

    @Autowired
    private CqlSession cqlSession;

    public void save(AccountSubscription accountSubscription) {
        String query = "INSERT INTO account_subscription (account_id, account_type, status, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = cqlSession.prepare(query);
        BoundStatement boundStatement = preparedStatement.bind(
                accountSubscription.getAccountId(),
                accountSubscription.getAccountType(),
                accountSubscription.getStatus(),
                accountSubscription.getCreatedAt(),
                accountSubscription.getUpdatedAt()
        );
        cqlSession.execute(boundStatement);
    }

    public Optional<AccountSubscription> findById(String accountId) {
        String query = "SELECT account_id, account_type, status, created_at, updated_at FROM account_subscription WHERE account_id = ?";
        PreparedStatement preparedStatement = cqlSession.prepare(query);
        BoundStatement boundStatement = preparedStatement.bind(accountId);
        ResultSet resultSet = cqlSession.execute(boundStatement);
        return Optional.ofNullable(resultSet.one()).map(row -> new AccountSubscription(
                row.getString("account_id"),
                row.getString("account_type"),
                row.getString("status"),
                row.getInstant("created_at"),
                row.getInstant("updated_at")
        ));
    }

    public void deleteById(String accountId) {
        String query = "DELETE FROM account_subscription WHERE account_id = ?";
        PreparedStatement preparedStatement = cqlSession.prepare(query);
        BoundStatement boundStatement = preparedStatement.bind(accountId);
        cqlSession.execute(boundStatement);
    }

    public List<AccountSubscription> findAll() {
        String query = "SELECT account_id, account_type, status, created_at, updated_at FROM account_subscription";
        ResultSet resultSet = cqlSession.execute(query);
        List<AccountSubscription> accounts = new ArrayList<>();
        resultSet.forEach(row -> accounts.add(new AccountSubscription(
                row.getString("account_id"),
                row.getString("account_type"),
                row.getString("status"),
                row.getInstant("created_at"),  // Pass Instant directly
                row.getInstant("updated_at")   // Pass Instant directly
        )));
        return accounts;
    }
}
