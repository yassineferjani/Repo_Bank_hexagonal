package services;

import api.AccountServicePort;
import api.CreditCardServicePort;
import api.TransactionServicePort;
import exception.AccountNotFoundException;
import exception.InsufficientfundsException;
import exception.TransactionNotFoundException;
import models.Transaction;
import models.TransactionType;
import org.springframework.stereotype.Service;
import spi.TransactionPersistencePort;

import javax.money.MonetaryAmount;
import java.time.Clock;
import java.time.OffsetDateTime;

@Service

public class CreditCardServiceImp implements CreditCardServicePort {

    private final AccountServicePort accountServicePort;
    private final TransactionPersistencePort transactionPersistencePort;
    private final TransactionServicePort transactionServicePort;
    private final Clock clock;

    public CreditCardServiceImp(final AccountServicePort accountServicePort,
                                final TransactionPersistencePort transactionPersistencePort,
                                final TransactionServicePort transactionServicePort, final Clock clock) {
        this.accountServicePort = accountServicePort;
        this.transactionPersistencePort = transactionPersistencePort;
        this.transactionServicePort = transactionServicePort;
        this.clock = clock;
    }


    @Override
    public void withdrawal(long rib, MonetaryAmount amount) {
        if (accountServicePort.getById(rib) == null)
            throw new AccountNotFoundException("Account not found");
        if (transactionServicePort.getBalance(rib).isLessThan(amount))
            throw new InsufficientfundsException("Insufficient funds:"+transactionServicePort.getBalance(rib)
                    +"must be greater than" + amount);
        transactionPersistencePort.save(Transaction.builder()
                .accountId(rib)
                .amount(amount)
                .dateTime(OffsetDateTime.now(clock))
                .type(TransactionType.Withdrawal)
                .build());
    }

    @Override
    public void deposit(long rib, MonetaryAmount amount) {
        if (accountServicePort.getById(rib) == null)
            throw new AccountNotFoundException("Account not found");
        transactionPersistencePort.save(Transaction.builder()
                .accountId(rib)
                .amount(amount)
                .dateTime(OffsetDateTime.now(clock))
                .type(TransactionType.Deposit)
                .build());
    }
    @Override
    public void deleteById(long id) {
        Transaction transaction = transactionPersistencePort.findById(id).orElseThrow(TransactionNotFoundException::new);
        if (transaction.getType() == TransactionType.Deposit)
            withdrawal(transaction.getAccountId(), transaction.getAmount());
        else
            deposit(transaction.getAccountId(), transaction.getAmount());
    }

}
