package be.rds.ezbanking.domain.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.rds.ezbanking.infrastructure.NextSequenceService;

@Component
public class TransactionFactory {
	
	@Autowired
	private NextSequenceService nextSeqService;
	
	public Transfer createTransfer(double amount,String creditAccountId, String debetAccountId){
		return new Transfer(amount, creditAccountId, debetAccountId, getSeqNr());
	}
	
	public Deposit createDeposit(double amount, String accountId){
		return new Deposit(amount, accountId, getSeqNr());
	}
	
	private int getSeqNr(){
		return nextSeqService.getNextSequence(Transaction.class.getName());
	}
}
