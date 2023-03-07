package api;

import models.Account;

import java.util.List;

public interface AccountServicePort {
    Account add(Account account);
    List<Account> getAll();
    Account getById(long id);
    void deleteById(long id);
    Account update(Account account);
}
