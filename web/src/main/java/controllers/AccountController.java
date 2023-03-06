package controllers;

import api.AccountServicePort;
import models.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("account/")
public class AccountController {
    private  AccountServicePort accountServicePort;

    public AccountController( AccountServicePort accountServicePort) {
        this.accountServicePort = accountServicePort;
    }

    @PostMapping("add")
    public ResponseEntity<Account> add(@RequestBody Account account){
        accountServicePort.add(account);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<Account>> getAll(){
        return new ResponseEntity<>(accountServicePort.getAll(),HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<Account> getById(@RequestParam("rib") long rib){
        return new ResponseEntity<>(accountServicePort.getById(rib),HttpStatus.OK);
    }

    @DeleteMapping("del")
    public ResponseEntity deleteById(@RequestParam("rib") long rib){
        accountServicePort.deleteById(rib);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PutMapping("update")
    public ResponseEntity<Account> update(@RequestBody  Account account){
        accountServicePort.update(account);
        return new ResponseEntity<>(account,HttpStatus.OK);
    }
}
