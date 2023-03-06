package api;

import models.Transaction;
import services.TransactionServiceImp;

import javax.money.MonetaryAmount;
import java.util.List;

public interface TransactionServicePort {
    List<Transaction> getAll();
    Transaction getById(long id);
    List<Transaction> findByAccountId(long id);
    MonetaryAmount getBalance(long rib);
    List<TransactionServiceImp.TransactionWithBalance> getTransactionsWithBalance(long rib);

}
