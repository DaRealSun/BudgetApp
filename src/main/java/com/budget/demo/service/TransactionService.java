package com.budget.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budget.demo.data.Transaction;
import com.budget.demo.data.TransactionType;
import com.budget.demo.repository.TransactionRepository;

@Service
public class TransactionService {
	private final TransactionRepository transactionRepository;
	
	@Autowired
	public TransactionService(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	public List<Transaction> getAllTransaction() {
		return transactionRepository.findAll();
	}
	
	public Transaction getTransactionById(Long id) {
		return transactionRepository.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Transaction id" + id));
	}
	
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}
	
	public Transaction updateTransaction(Transaction transaction, Long id) {
		Transaction transactionUpdate = getTransactionById(id);
		transactionUpdate.setAmount(transaction.getAmount());
		transactionUpdate.setDescription(transaction.getDescription());
		transactionUpdate.setType(transaction.getType());
		transactionUpdate.setDate(transaction.getDate());
		return transactionRepository.save(transactionUpdate);
	}
	
	public void deleteTransaction(Long id) {
		transactionRepository.deleteById(id);
	}
	
	  public double calculateTotalIncome() {
	        return transactionRepository.findAll()
	                .stream()
	                .filter(t -> t.getType().equals(TransactionType.INCOME))
	                .mapToDouble(Transaction::getAmount)
	                .sum();
	    }

	    public double calculateTotalExpense() {
	        return transactionRepository.findAll()
	                .stream()
	                .filter(t -> t.getType().equals(TransactionType.EXPENSE))
	                .mapToDouble(Transaction::getAmount)
	                .sum();
	    }
}
