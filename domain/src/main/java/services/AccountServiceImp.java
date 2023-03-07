package services;

import api.AccountServicePort;
import exception.AccountNotFoundException;
import models.Account;
import org.springframework.stereotype.Service;
import spi.AccountPersistencePort;

import java.util.List;

@Service
public class AccountServiceImp implements AccountServicePort {
    private final AccountPersistencePort accountPersistencePort;

    public AccountServiceImp(final AccountPersistencePort accountPersistencePort) {
        this.accountPersistencePort = accountPersistencePort;
    }

    @Override
    public Account getById(long rib){
        return accountPersistencePort.findById(rib).orElseThrow(()->new AccountNotFoundException("Account not exist "+rib));
    }
    @Override
    public Account add (Account account){
        return accountPersistencePort.save(account);
    }
    @Override
    public void deleteById(long rib){
        accountPersistencePort.deleteById(rib);
    }

    @Override
    public Account update(Account account) {
        return accountPersistencePort.save(account);
    }
    @Override
    public List<Account> getAll(){
        return accountPersistencePort.findAll();
    }

}
