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
    public TransactionServiceImp.TransactionWithBalance withdrawal(long rib, MonetaryAmount amount) {
        if (accountServicePort.getById(rib) == null)
            throw new AccountNotFoundException("Account not found");
        if (transactionServicePort.getBalance(rib).isLessThan(amount))
            throw new InsufficientfundsException("Insufficient funds:"+transactionServicePort.getBalance(rib)
                    +"must be greater than" + amount);
        Transaction transaction = Transaction.builder()
                .accountId(rib)
                .amount(amount)
                .dateTime(OffsetDateTime.now(clock))
                .type(TransactionType.Withdrawal)
                .build();
        transactionPersistencePort.save(transaction);
        return TransactionServiceImp.TransactionWithBalance.builder()
                .amount(amount)
                .balance(transactionServicePort.getBalance(rib))
                .dateTime(OffsetDateTime.now(clock))
                .build();
    }

    @Override
    public TransactionServiceImp.TransactionWithBalance deposit(long rib, MonetaryAmount amount) {
        if (accountServicePort.getById(rib) == null)
            throw new AccountNotFoundException("Account not found");
        transactionPersistencePort.save(Transaction.builder()
                .accountId(rib)
                .amount(amount)
                .dateTime(OffsetDateTime.now(clock))
                .type(TransactionType.Deposit)
                .build());
        return TransactionServiceImp.TransactionWithBalance.builder()
                .amount(amount)
                .balance(transactionServicePort.getBalance(rib))
                .dateTime(OffsetDateTime.now(clock))
                .build();
    }
    @Override
    public void deleteById(long id) {
        Transaction transaction = transactionPersistencePort.findById(id).orElseThrow(()->new TransactionNotFoundException("Transaction not exist "+id));
        if (transaction.type() == TransactionType.Deposit)
            withdrawal(transaction.accountId(), transaction.amount());
        else
            deposit(transaction.accountId(), transaction.amount());
    }

}
