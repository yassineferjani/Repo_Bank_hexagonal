package models;

import lombok.Builder;
import lombok.Value;

import javax.money.MonetaryAmount;
import java.time.OffsetDateTime;

@Builder
@Value
public class Transaction {
    long id;
    MonetaryAmount amount;
    long accountId;
    TransactionType type;
    OffsetDateTime dateTime;

}
