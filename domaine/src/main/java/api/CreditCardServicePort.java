package api;

import javax.money.MonetaryAmount;

public interface CreditCardServicePort {
    void withdrawal(long rib, MonetaryAmount amount);
    void deposit(long rib, MonetaryAmount amount);
    void deleteById(long id);
}
