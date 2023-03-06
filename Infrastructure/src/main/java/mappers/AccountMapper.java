package mappers;

import entities.Account;

import java.util.Collections;

public class AccountMapper {

    public static Account accountDTOToAccount(models.Account account){

        return new Account(account.id(), account.type(), Collections.emptyList());
    }

    public static models.Account accountToAccountDTO(Account account){
        return models.Account.builder()
                .id(account.getId())
                .type(account.getType())
                .build();
    }
}
