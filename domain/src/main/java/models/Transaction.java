package models;

import javax.money.MonetaryAmount;
import java.time.OffsetDateTime;

public record Transaction( long id,
        MonetaryAmount amount,
        long accountId,
        TransactionType type,
        OffsetDateTime dateTime) {



    public static TransactionBuilder builder() {
        return new TransactionBuilder();
    }

    public static class TransactionBuilder {
        private long id;
        private MonetaryAmount amount;
        private long accountId;
        private TransactionType type;
        private OffsetDateTime dateTime;

        TransactionBuilder() {
        }

        public TransactionBuilder id(long id) {
            this.id = id;
            return this;
        }

        public TransactionBuilder amount(MonetaryAmount amount) {
            this.amount = amount;
            return this;
        }

        public TransactionBuilder accountId(long accountId) {
            this.accountId = accountId;
            return this;
        }

        public TransactionBuilder type(TransactionType type) {
            this.type = type;
            return this;
        }

        public TransactionBuilder dateTime(OffsetDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }

        public Transaction build() {
            return new Transaction(id, amount, accountId, type, dateTime);
        }

        public String toString() {
            return "Transaction.TransactionBuilder(id=" + this.id + ", amount=" + this.amount + ", accountId=" + this.accountId + ", type=" + this.type + ", dateTime=" + this.dateTime + ")";
        }
    }
}
