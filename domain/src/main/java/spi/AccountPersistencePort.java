package spi;

import models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountPersistencePort {
    Account save(Account account);
    List<Account> findAll();
    Optional<Account> findById(long id);
    void deleteById(long id);
    Account update(Account account);
}
