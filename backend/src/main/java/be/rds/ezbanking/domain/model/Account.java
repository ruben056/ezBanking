package be.rds.ezbanking.domain.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

public class Account {
	
	@Id
	private String id;	
	private String accountHolder;
	private List<Transaction> transactions = new ArrayList<>();
	
	public Account() {
	}
	
	public Account(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	public String getId() {
		return id;
	}
	
	public String getAccountHolder() {
		return accountHolder;
	}
	
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public List<? extends Transaction> getTransactions() {
		return transactions;
	}

	public void addTransaction(Transaction t) {
		transactions.add(t);
	}
	
	public double getBalance() {
		return transactions.stream().mapToDouble(
				t -> t.getAmountForAccount(this))
				.sum();
	}
}
