package mappers;

import entities.Account;
import entities.Transaction;

public class TransactionMapper {

    public static Transaction transactionDTOtoTransaction(models.Transaction transaction, Account account){
        return new  Transaction(transaction.id(),
                transaction.amount(),transaction.type(),transaction.dateTime(), account);
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
