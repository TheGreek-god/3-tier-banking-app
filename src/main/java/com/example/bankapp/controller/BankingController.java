package com.example.bankapp.controller;

import com.example.bankapp.model.Account;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BankingController {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/deposit")
    public String deposit(@RequestParam Double amount) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        Account account = getOrCreateAccount(username);
        account.deposit(amount);
        accountRepository.save(account);
        
        // Record transaction
        Transaction transaction = new Transaction(username, "DEPOSIT", amount, "Cash deposit");
        transactionRepository.save(transaction);
        
        return "redirect:/dashboard?success=Deposited+$" + amount + ".+New+balance:+$" + account.getBalance();
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam Double amount) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        Account account = getOrCreateAccount(username);
        
        if (account.canWithdraw(amount)) {
            account.withdraw(amount);
            accountRepository.save(account);
            
            // Record transaction
            Transaction transaction = new Transaction(username, "WITHDRAW", amount, "Cash withdrawal");
            transactionRepository.save(transaction);
            
            return "redirect:/dashboard?success=Withdrew+$" + amount + ".+New+balance:+$" + account.getBalance();
        } else {
            return "redirect:/dashboard?error=Insufficient+funds.+You+have+$" + account.getBalance();
        }
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam String toUsername, @RequestParam Double amount) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String fromUsername = authentication.getName();
        
        if (fromUsername.equals(toUsername)) {
            return "redirect:/dashboard?error=Cannot+transfer+to+yourself";
        }
        
        Account fromAccount = getOrCreateAccount(fromUsername);
        Account toAccount = getOrCreateAccount(toUsername);
        
        if (fromAccount.canWithdraw(amount)) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
            
            // Record transactions for both users
            Transaction fromTransaction = new Transaction(fromUsername, "TRANSFER", amount, "Transfer to " + toUsername, toUsername);
            Transaction toTransaction = new Transaction(toUsername, "TRANSFER", amount, "Transfer from " + fromUsername, fromUsername);
            
            transactionRepository.save(fromTransaction);
            transactionRepository.save(toTransaction);
            
            return "redirect:/dashboard?success=Transferred+$" + amount + "+to+" + toUsername + ".+New+balance:+$" + fromAccount.getBalance();
        } else {
            return "redirect:/dashboard?error=Insufficient+funds+for+transfer.+You+have+$" + fromAccount.getBalance();
        }
    }
    
    private Account getOrCreateAccount(String username) {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            account = new Account(username);
            accountRepository.save(account);
        }
        return account;
    }
}