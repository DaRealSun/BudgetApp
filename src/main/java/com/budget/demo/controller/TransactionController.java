package com.budget.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.budget.demo.data.Transaction;
import com.budget.demo.data.User;
import com.budget.demo.service.TransactionService;
import com.budget.demo.service.UserService;

@Controller
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public String listTransactions(Model model) {
        List<Transaction> transactions = transactionService.getAllTransaction();
        double totalIncome = transactionService.calculateTotalIncome();
        double totalExpense = transactionService.calculateTotalExpense();

        model.addAttribute("transactions", transactions);
        model.addAttribute("totalIncome", totalIncome);
        model.addAttribute("totalExpense", totalExpense);

        return "transactions";
    }

    @GetMapping("/transaction/new")
    public String newTransactionForm(Model model) {
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        return "new_transaction"; 
    }

    @PostMapping("/transaction")
    public String saveTransaction(@ModelAttribute("transaction") Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/transaction/edit/{id}")
    public String editTransactionForm(@PathVariable Long id, Model model) {
        Transaction transaction = transactionService.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "edit_transaction"; 
    }

    @PostMapping("/transaction/{id}")
    public String updateTransaction(@ModelAttribute("transaction") Transaction transaction,
                             @PathVariable Long id) {
        // set the id to the transaction
        transaction.setId(id);
        transactionService.saveTransaction(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/transaction/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/transactions";
    }
}
