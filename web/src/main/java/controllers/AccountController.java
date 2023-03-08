package controllers;

import api.AccountServicePort;
import exception.AccountNotFoundException;
import models.Account;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import validation.OnCreate;
import validation.OnUpdate;

import java.util.List;

@RestController
@RequestMapping("account/")
public class AccountController {
    private final AccountServicePort accountServicePort;

    public AccountController( final AccountServicePort accountServicePort) {
        this.accountServicePort = accountServicePort;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Account add(@RequestBody @Validated(OnCreate.class) Account account){
        return accountServicePort.add(account);
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAll(){
        return accountServicePort.getAll();
    }

    @GetMapping("{rib}")
    @ResponseStatus(HttpStatus.OK)
    public Account getById(@PathVariable long rib){
        return accountServicePort.getById(rib).orElseThrow(()->new AccountNotFoundException("Account not found"+rib));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@RequestParam("rib") long rib){
        accountServicePort.deleteById(rib);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Account update(@RequestBody @Validated(OnUpdate.class) Account account){
        return accountServicePort.update(account);
    }
}
