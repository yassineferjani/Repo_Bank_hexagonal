package services;

import api.TransactionServicePort;
import exception.TransactionNotFoundException;
import models.Transaction;
import models.TransactionType;
import org.javamoney.moneta.Money;
import org.springframework.stereotype.Service;
import spi.TransactionPersistencePort;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImp implements TransactionServicePort {

    private final TransactionPersistencePort transactionPersistencePort;
    private final CurrencyUnit unit;

    public TransactionServiceImp(final TransactionPersistencePort transactionPersistencePort, final CurrencyUnit unit) {
        this.transactionPersistencePort = transactionPersistencePort;
        this.unit = unit;
    }

    @Override
    public List<Transaction> getByAccountId(long rib){
        return transactionPersistencePort.findAll().stream().filter(t->t.getAccountId()==rib).collect(Collectors.toList());
    }

    @Override
    public List<Transaction> getAll() {
        return transactionPersistencePort.findAll();
    }

    @Override
    public Transaction getById(long id){

        return transactionPersistencePort.findById(id).orElseThrow(TransactionNotFoundException::new);
    }

    public MonetaryAmount calcBalance(Transaction transaction) {
        return (transaction.getType() == TransactionType.Withdrawal)
                ? transaction.getAmount().negate()
                : transaction.getAmount();
    }

    public MonetaryAmount getBalance(long rib) {
        return getByAccountId(rib).stream()
                .map(this::calcBalance)
                .reduce(Money.of(0, unit), MonetaryAmount::add);
    }

    public List<TransactionWithBalance> getTransactionsWithBalance(long rib) {
        AtomicReference<MonetaryAmount> balance = new AtomicReference<>(Money.of(0, unit));
        return getByAccountId(rib).stream().sorted(Comparator.comparing(Transaction::getDateTime))
                .map(t -> {
                    MonetaryAmount currentBalance = balance.accumulateAndGet(calcBalance(t), MonetaryAmount::add);
                    return new TransactionWithBalance(t.getDateTime(),t.getAmount(), currentBalance);
                })
                .collect(Collectors.toList());
    }

    public record TransactionWithBalance(OffsetDateTime dateTime,  MonetaryAmount amount,MonetaryAmount balance) {

    }


}
