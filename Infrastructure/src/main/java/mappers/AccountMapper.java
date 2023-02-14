package mappers;

import entities.Account;

public class AccountMapper {

    public static Account accountDTOToAccount(models.Account account){

        return Account.builder()
                .type(account.getType())
                .id(account.getId())
                .build();
    }

    public static models.Account accountToAccountDTO(Account account){

        return models.Account.builder()
                .type(account.getType())
                .id(account.getId())
                .build();
    }
}
