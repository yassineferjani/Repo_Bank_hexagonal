package controllers;

import api.CreditCardServicePort;
import api.TransactionServicePort;
import models.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.TransactionServiceImp;

import javax.money.MonetaryAmount;
import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {
    private  TransactionServicePort transactionServicePort;
    private  CreditCardServicePort creditCardServicePort;

    public TransactionController( TransactionServicePort transactionServicePort,
                                 CreditCardServicePort creditCardServicePort) {
        this.transactionServicePort = transactionServicePort;
        this.creditCardServicePort = creditCardServicePort;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAll (){
        return new ResponseEntity<>(transactionServicePort.getAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Transaction> getById(@RequestParam("id") long id){
        return new ResponseEntity<>(transactionServicePort.getById(id),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Transaction>> getByAccountId(@RequestParam("rib") long rib){
        return new ResponseEntity<>(transactionServicePort.getByAccountId(rib),HttpStatus.OK);
    }

    @GetMapping("/balance")
    public ResponseEntity<MonetaryAmount> getBalance(@RequestParam("rib") long rib) {
        return new ResponseEntity<>(transactionServicePort.getBalance(rib),HttpStatus.OK);
    }

    @GetMapping("statement")
    public ResponseEntity<List<TransactionServiceImp.TransactionWithBalance>> getStatement(@RequestParam("rib") long rib){
        return new ResponseEntity<>(transactionServicePort.getTransactionsWithBalance(rib),HttpStatus.OK);
    }

    @PostMapping("/withdrawal")
    public ResponseEntity withdrawal(@RequestParam("rib") long rib,
                                     @RequestParam("amount") MonetaryAmount amount){
        creditCardServicePort.withdrawal(rib,amount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestParam("rib") long rib,
                                     @RequestParam("amount") MonetaryAmount amount){
        creditCardServicePort.deposit(rib,amount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam("id") long id){
        creditCardServicePort.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
