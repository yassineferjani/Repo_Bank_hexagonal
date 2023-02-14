/*
package serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import models.Account;
import models.Type;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.AccountServiceImp;
import spi.AccountPersistencePort;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountPersistencePort accountPersistencePort;

    @Test
    public void testGetById() {
        // Arrange
        long rib = 1234567890123456L;
        Account account = Account.builder().id(rib).type(Type.Courant).build();
        when(accountPersistencePort.findById(rib)).thenReturn(Optional.of(account));
        AccountServiceImp accountService = new AccountServiceImp(accountPersistencePort);

        // Act
        Account result = accountService.getById(rib);

        // Assert
        assertEquals(account, result);
    }

    @Test
    public void testAdd() {
        // Arrange
        Account account = Account.builder().id(1L).type(Type.Courant).build();
        AccountServiceImp accountService = new AccountServiceImp(accountPersistencePort);

        // Act
        accountService.add(account);

        // Assert
        verify(accountPersistencePort).save(account);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        long rib = 1234567890123456L;
        AccountServiceImp accountService = new AccountServiceImp(accountPersistencePort);

        // Act
        accountService.deleteById(rib);

        // Assert
        verify(accountPersistencePort).deleteById(rib);
    }

    @Test
    public void testUpdate() {
        // Arrange
        Account account = Account.builder().id(1L).type(Type.Courant).build();
        AccountServiceImp accountService = new AccountServiceImp(accountPersistencePort);

        // Act
        accountService.update(account);

        // Assert
        verify(accountPersistencePort).save(account);
    }

    @Test
    public void testGetAll() {
        // Arrange
        List<Account> accounts = Collections.singletonList(Account.builder().id(1L).type(Type.Courant).build());
        when(accountPersistencePort.findAll()).thenReturn(accounts);
        AccountServiceImp accountService = new AccountServiceImp(accountPersistencePort);

        // Act
        List<Account> result = accountService.getAll();

        // Assert
        assertEquals(accounts, result);
    }
}*/
