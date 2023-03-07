package spi;

import models.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionPersistencePort {
    Transaction save(Transaction transaction);
    List<Transaction> findAll();
    Optional<Transaction> findById(long id);
    List<Transaction> findByAccountId(long id);
}
