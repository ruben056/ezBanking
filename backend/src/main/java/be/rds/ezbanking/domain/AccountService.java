package be.rds.ezbanking.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.rds.ezbanking.domain.model.Account;
import be.rds.ezbanking.domain.model.Transaction;
import be.rds.ezbanking.domain.model.TransactionFactory;

@Service
public class AccountService {

	@Autowired
	private TransactionFactory transactionFactory;
	
	public List<Account> transferMoney(double amount, Account creditAccount, Account debetAccount){

		final Transaction transfer = transactionFactory.createTransfer(amount, creditAccount.getId(),
				debetAccount.getId());
		
		return Arrays.asList(creditAccount, debetAccount).stream().map(acc -> {
			acc.addTransaction(transfer);
			return acc;
		}).collect(Collectors.toList());
	}
	
	public Account makeDeposit(double amount, Account account) {
		
		Transaction deposit = transactionFactory.createDeposit(amount, account.getId());
		account.addTransaction(deposit);
		return account;
	}

	
}
