package api;

import models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountServicePort {
    Account add(Account account);
    List<Account> getAll();
    Optional<Account> getById(long id);
    void deleteById(long id);
    Account update(Account account);
}
