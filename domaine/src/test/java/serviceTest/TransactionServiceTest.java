/*
package serviceTest;

import models.Transaction;
import models.TransactionType;
import org.javamoney.moneta.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import services.TransactionServiceImp;
import spi.TransactionPersistencePort;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {
    @Mock
    private TransactionPersistencePort transactionPersistencePort;

    private TransactionServiceImp transactionService;

    private final CurrencyUnit unit = Monetary.getCurrency("EUR");
    private final Clock clock = Clock.systemUTC();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionService = new TransactionServiceImp(transactionPersistencePort, unit);
    }

    @Test
    void testGetByAccountId() {
        // Arrange
        long rib = 123456789;
        Transaction t1 = Transaction.builder().id(1L).accountId(rib).build();
        Transaction t2 = Transaction.builder().id(2L).accountId(rib).build();
        when(transactionPersistencePort.findAll()).thenReturn(Arrays.asList(t1, t2));

        // Act
        List<Transaction> result = transactionService.getByAccountId(rib);

        // Assert
        assertEquals(2, result.size());
        assertEquals(t1, result.get(0));
        assertEquals(t2, result.get(1));
    }

    @Test
    void testGetAll() {
        // Arrange
        Transaction t1 = Transaction.builder().id(1L).accountId(1L).build();
        Transaction t2 = Transaction.builder().id(2L).accountId(1L).build();
        when(transactionPersistencePort.findAll()).thenReturn(Arrays.asList(t1, t2));

        // Act
        List<Transaction> result = transactionService.getAll();

        // Assert
        assertEquals(2, result.size());
        assertEquals(t1, result.get(0));
        assertEquals(t2, result.get(1));
    }

    @Test
    void testGetById() {
        // Arrange
        long id = 1L;
        Transaction t = Transaction.builder().id(1L).accountId(1L).build();
        when(transactionPersistencePort.findById(id)).thenReturn(Optional.of(t));

        // Act
        Transaction result = transactionService.getById(id);

        // Assert
        assertEquals(t, result);
    }

    @Test
    void testCalcBalance() {
        // Arrange
        Transaction withdrawal = Transaction.builder().id(1L).accountId(1L).amount(Money.of(100,unit))
                .type(TransactionType.Withdrawal).build();
        Transaction deposit = Transaction.builder().id(1L).accountId(1L).amount(Money.of(50,unit))
                .type(TransactionType.Deposit).build();
        // Act
        MonetaryAmount result1 = transactionService.calcBalance(withdrawal);
        MonetaryAmount result2 = transactionService.calcBalance(deposit);

        // Assert
        assertEquals(Money.of(new BigDecimal("-100"), unit), result1);
        assertEquals(Money.of(new BigDecimal("50"), unit), result2);
    }


    @Test
    void testGetBalance() {
        // setup
        TransactionServiceImp transactionServiceImp = new TransactionServiceImp(transactionPersistencePort, unit);
        Transaction t1 = Transaction.builder().id(1L).accountId(1L).type(TransactionType.Deposit).amount(Money.of(100,unit)).dateTime(OffsetDateTime.now(clock)).build();
        Transaction t2 = Transaction.builder().id(2L).accountId(1L).type(TransactionType.Withdrawal).amount(Money.of(50,unit)).dateTime(OffsetDateTime.now(clock)).build();
        Transaction t3 = Transaction.builder().id(3L).accountId(1L).type(TransactionType.Withdrawal).amount(Money.of(25,unit)).dateTime(OffsetDateTime.now(clock)).build();

        when(transactionPersistencePort.findAll()).thenReturn(Arrays.asList(t1,t2,t3));

        // execution
        MonetaryAmount balance = transactionServiceImp.getBalance(1L);

        // verification
        assertEquals(Money.of(BigDecimal.valueOf(25), unit), balance);
    }

    @Test
    void testGetTransactionsWithBalance() {
        // setup
        TransactionServiceImp transactionServiceImp = new TransactionServiceImp(transactionPersistencePort, unit);
        Transaction t1 = Transaction.builder().id(1L).accountId(1L).type(TransactionType.Deposit).amount(Money.of(120,unit)).dateTime(OffsetDateTime.now(clock)).build();
        Transaction t2 = Transaction.builder().id(2L).accountId(1L).type(TransactionType.Withdrawal).amount(Money.of(50,unit)).dateTime(OffsetDateTime.now(clock)).build();
        Transaction t3 = Transaction.builder().id(3L).accountId(1L).type(TransactionType.Withdrawal).amount(Money.of(25,unit)).dateTime(OffsetDateTime.now(clock)).build();

        when(transactionPersistencePort.findAll()).thenReturn(Arrays.asList(t1,t2,t3));

        // execution
        List<TransactionServiceImp.TransactionWithBalance> transactionsWithBalance = transactionServiceImp.getTransactionsWithBalance(1L);
        // verification
        assertEquals(3, transactionsWithBalance.size());
        assertEquals(Money.of(70,unit),transactionsWithBalance.get(1).balance());

    }
}

*/
