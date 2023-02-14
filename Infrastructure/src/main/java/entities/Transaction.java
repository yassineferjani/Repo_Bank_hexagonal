package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.time.OffsetDateTime;
import models.TransactionType;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private MonetaryAmount amount;
    private TransactionType type;
    private OffsetDateTime dateTime;

    @OneToMany
    private Account account;

}
