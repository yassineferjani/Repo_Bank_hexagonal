package api;

import services.TransactionServiceImp;

import javax.money.MonetaryAmount;

public interface CreditCardServicePort {
    TransactionServiceImp.TransactionWithBalance withdrawal(long rib, MonetaryAmount amount);
    TransactionServiceImp.TransactionWithBalance deposit(long rib, MonetaryAmount amount);
    void deleteById(long id);
}
