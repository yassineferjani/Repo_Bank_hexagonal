package entities;

import models.Type;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Type type;
    @OneToMany (mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Account(long id, Type type, List<Transaction> transactions) {
        this.id = id;
        this.type = type;
        this.transactions = transactions;
    }

    public Account() {
    }

    public long getId() {
        return this.id;
    }

    public Type getType() {
        return this.type;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Account)) return false;
        final Account other = (Account) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$transactions = this.getTransactions();
        final Object other$transactions = other.getTransactions();
        if (this$transactions == null ? other$transactions != null : !this$transactions.equals(other$transactions))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Account;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * PRIME + (int) ($id >>> 32 ^ $id);
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $transactions = this.getTransactions();
        result = result * PRIME + ($transactions == null ? 43 : $transactions.hashCode());
        return result;
    }

    public String toString() {
        return "Account(id=" + this.getId() + ", type=" + this.getType() + ", transactions=" + this.getTransactions() + ")";
    }
}
