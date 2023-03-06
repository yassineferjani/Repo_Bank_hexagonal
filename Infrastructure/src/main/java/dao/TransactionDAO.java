package dao;

import entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction,Long> {
    List<models.Transaction> findByAccountId(long id);
}
