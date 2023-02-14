package adapters;

import dao.TransactionDAO;
import entities.Account;
import mappers.AccountMapper;
import mappers.TransactionMapper;
import models.Transaction;
import spi.AccountPersistencePort;
import spi.TransactionPersistencePort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionAdapter implements TransactionPersistencePort {
    private final TransactionDAO transactionDAO;
    private final AccountPersistencePort accountPersistencePort;

    public TransactionAdapter(final TransactionDAO transactionDAO, final AccountPersistencePort accountPersistencePort) {
        this.transactionDAO = transactionDAO;
        this.accountPersistencePort = accountPersistencePort;
    }

    @Override
    public void save(Transaction transaction) {
        Account account = AccountMapper.accountDTOToAccount(accountPersistencePort.findById(transaction.getAccountId()).get());
        transactionDAO.save(TransactionMapper.transactionDTOtoTransaction(transaction,account));

    }

    @Override
    public List<Transaction> findAll() {
        return transactionDAO.findAll().stream()
                .map(TransactionMapper::transactionToTransactionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Transaction> findById(long id) {
        return Optional.of(TransactionMapper.transactionToTransactionDTO(transactionDAO.findById(id).get()));
    }
}
