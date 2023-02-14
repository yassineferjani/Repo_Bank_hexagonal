package mappers;

import entities.Account;
import entities.Transaction;

public class TransactionMapper {

    public static Transaction transactionDTOtoTransaction(models.Transaction transaction, Account account){
        return Transaction.builder()
                .id(transaction.getId())
                .type(transaction.getType())
                .account(account)
                .dateTime(transaction.getDateTime())
                .amount(transaction.getAmount())
                .build();
    }

    public static models.Transaction transactionToTransactionDTO(Transaction transaction){
        return models.Transaction.builder()
                .dateTime(transaction.getDateTime())
                .id(transaction.getId())
                .accountId(transaction.getAccount().getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .build();
    }
}
