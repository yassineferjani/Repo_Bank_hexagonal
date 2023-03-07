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
@RequestMapping("transaction/")
public class TransactionController {
    private final TransactionServicePort transactionServicePort;
    private final CreditCardServicePort creditCardServicePort;

    public TransactionController( final TransactionServicePort transactionServicePort,
                                final CreditCardServicePort creditCardServicePort) {
        this.transactionServicePort = transactionServicePort;
        this.creditCardServicePort = creditCardServicePort;
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getAll (){
        return transactionServicePort.getAll();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Transaction getById(@RequestParam("id") long id){
        return transactionServicePort.getById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Transaction> getByAccountId(@RequestParam("rib") long rib){
        return transactionServicePort.findByAccountId(rib);
    }

    @GetMapping("/balance")
    @ResponseStatus(HttpStatus.OK)
    public MonetaryAmount getBalance(@RequestParam("rib") long rib) {
        return transactionServicePort.getBalance(rib);
    }

    @GetMapping("statement")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionServiceImp.TransactionWithBalance> getStatement(@RequestParam("rib") long rib){
        return transactionServicePort.getTransactionsWithBalance(rib);
    }

    @PostMapping("/withdrawal")
    @ResponseStatus(HttpStatus.OK)
    public TransactionServiceImp.TransactionWithBalance withdrawal(@RequestParam("rib") long rib,
                                                                   @RequestParam("amount") MonetaryAmount amount){
       return creditCardServicePort.withdrawal(rib,amount);

    }

    @PostMapping("/deposit")
    @ResponseStatus(HttpStatus.OK)
    public TransactionServiceImp.TransactionWithBalance deposit(@RequestParam("rib") long rib,
                                                                @RequestParam("amount") MonetaryAmount amount){
        return creditCardServicePort.deposit(rib,amount);
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam("id") long id){
        creditCardServicePort.deleteById(id);
    }
}
