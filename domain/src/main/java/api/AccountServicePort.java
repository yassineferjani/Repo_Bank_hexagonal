package api;

import models.Account;

import java.util.List;

public interface AccountServicePort {
    void add(Account account);
    List<Account> getAll();
    Account getById(long id);
    void deleteById(long id);
    void update(Account account);
}
