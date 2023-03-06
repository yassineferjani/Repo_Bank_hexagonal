package models;

public record Account (long id, Type type) {



    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    public static class AccountBuilder {
        private long id;
        private Type type;

        AccountBuilder() {
        }

        public AccountBuilder id(long id) {
            this.id = id;
            return this;
        }

        public AccountBuilder type(Type type) {
            this.type = type;
            return this;
        }

        public Account build() {
            return new Account(id, type);
        }

        public String toString() {
            return "Account.AccountBuilder(id=" + this.id + ", type=" + this.type + ")";
        }
    }
}
