/*
package serviceTest;

import api.AccountServicePort;
import api.TransactionServicePort;
import models.Account;
import models.Transaction;
import models.TransactionType;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.CreditCardServiceImp;
import spi.TransactionPersistencePort;

import javax.money.MonetaryAmount;
import java.time.Clock;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CreditCardServiceTest {


    @Mock
    private TransactionServicePort transactionServicePort;
    @Mock
    private TransactionPersistencePort transactionPersistencePort;
    @Mock
    private AccountServicePort accountServicePort;
    @Mock
    private Clock clock;


    private CreditCardServiceImp creditCardServiceImp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clock = Clock.fixed(Instant.now(), ZoneId.of("UTC"));
        creditCardServiceImp = new CreditCardServiceImp(accountServicePort,transactionPersistencePort,transactionServicePort,clock);

    }

    @Test
    public void testDeposit() {

        long rib = 1L;
        MonetaryAmount amount = Money.of(100, "EUR");
        Account account = Account.builder().id(rib).build();

        when(accountServicePort.getById(rib)).thenReturn(account);
        Transaction transaction = Transaction.builder().accountId(rib).dateTime(OffsetDateTime.now(clock))
                .amount(amount).type(TransactionType.Deposit).build();

        creditCardServiceImp.deposit(rib, amount);
        verify(transactionPersistencePort).save(transaction);
    }

  @Test
    public void testwithdrawal() {

        long rib = 1L;
        MonetaryAmount amount = Money.of(50, "EUR");
        Account account = Account.builder().id(rib).build();

        when(accountServicePort.getById(rib)).thenReturn(account);
        when(transactionServicePort.getBalance(rib)).thenReturn(Money.of(100,"EUR"));
        Transaction transaction = Transaction.builder().accountId(rib).dateTime(OffsetDateTime.now(clock))
                .amount(amount).type(TransactionType.Withdrawal).build();

        creditCardServiceImp.withdrawal(rib, amount);
        verify(transactionPersistencePort).save(transaction);
    }

    @Test
    public void testDeleteById() {
        long rib = 1L;
        Account account = Account.builder().id(rib).build();
        Transaction transactionToDelete = Transaction.builder().id(1L).accountId(rib).dateTime(OffsetDateTime.now(clock))
                .amount(Money.of(50,"EUR")).type(TransactionType.Withdrawal).build();

        when(accountServicePort.getById(rib)).thenReturn(account);
        when(transactionPersistencePort.findById(1L)).thenReturn(Optional.of(transactionToDelete));
        creditCardServiceImp.deleteById(1L);
        Transaction transaction = Transaction.builder().accountId(rib).dateTime(OffsetDateTime.now(clock))
                .amount(Money.of(50,"EUR")).type(TransactionType.Deposit).build();
        verify(transactionPersistencePort).save(transaction);
    }




}

*/
