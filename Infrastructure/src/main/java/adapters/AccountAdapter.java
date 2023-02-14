package adapters;

import dao.AccountDAO;
import mappers.AccountMapper;
import models.Account;
import spi.AccountPersistencePort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountAdapter implements AccountPersistencePort {
    private final AccountDAO accountDAO;

    public AccountAdapter(final AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public void save(Account account) {
        accountDAO.save(AccountMapper.accountDTOToAccount(account));
    }

    @Override
    public List<Account> findAll() {
        return accountDAO.findAll().stream()
                .map(AccountMapper::accountToAccountDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Account> findById(long id) {
        return Optional.of(AccountMapper.accountToAccountDTO(accountDAO.findById(id).get()));
    }

    @Override
    public void deleteById(long id) {
        accountDAO.deleteById(id);
    }

    @Override
    public void update(Account account) {
        accountDAO.save(AccountMapper.accountDTOToAccount(account));

    }
}
