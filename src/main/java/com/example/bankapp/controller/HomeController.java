package com.example.bankapp.controller;

import com.example.bankapp.model.Account;
import com.example.bankapp.model.Transaction;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/")
    public String home() {
        return "login";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        Account account = getOrCreateAccount(username);
        model.addAttribute("account", account);
        return "dashboard";
    }
    
    @GetMapping("/transactions")
    public String transactions(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        
        Account account = getOrCreateAccount(username);
        List<Transaction> transactions = transactionRepository.findByUsernameOrderByTimestampDesc(username);
        
        model.addAttribute("account", account);
        model.addAttribute("transactions", transactions);
        model.addAttribute("username", username);
        return "transactions";
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