package spi;

import models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountPersistencePort {
    void save(Account account);
    List<Account> findAll();
    Optional<Account> findById(long id);
    void deleteById(long id);
    void update(Account account);
}
