package entities;

import models.TransactionType;

import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private MonetaryAmount amount;
    private TransactionType type;
    private OffsetDateTime dateTime;

    @OneToMany
    private Account account;

    public Transaction(long id, MonetaryAmount amount, TransactionType type, OffsetDateTime dateTime, Account account) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.dateTime = dateTime;
        this.account = account;
    }

    public Transaction() {
    }

    public long getId() {
        return this.id;
    }

    public MonetaryAmount getAmount() {
        return this.amount;
    }

    public TransactionType getType() {
        return this.type;
    }

    public OffsetDateTime getDateTime() {
        return this.dateTime;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAmount(MonetaryAmount amount) {
        this.amount = amount;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Transaction)) return false;
        final Transaction other = (Transaction) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$amount = this.getAmount();
        final Object other$amount = other.getAmount();
        if (this$amount == null ? other$amount != null : !this$amount.equals(other$amount)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$dateTime = this.getDateTime();
        final Object other$dateTime = other.getDateTime();
        if (this$dateTime == null ? other$dateTime != null : !this$dateTime.equals(other$dateTime)) return false;
        final Object this$account = this.getAccount();
        final Object other$account = other.getAccount();
        if (this$account == null ? other$account != null : !this$account.equals(other$account)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Transaction;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * PRIME + (int) ($id >>> 32 ^ $id);
        final Object $amount = this.getAmount();
        result = result * PRIME + ($amount == null ? 43 : $amount.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $dateTime = this.getDateTime();
        result = result * PRIME + ($dateTime == null ? 43 : $dateTime.hashCode());
        final Object $account = this.getAccount();
        result = result * PRIME + ($account == null ? 43 : $account.hashCode());
        return result;
    }

    public String toString() {
        return "Transaction(id=" + this.getId() + ", amount=" + this.getAmount() + ", type=" + this.getType() + ", dateTime=" + this.getDateTime() + ", account=" + this.getAccount() + ")";
    }
}
