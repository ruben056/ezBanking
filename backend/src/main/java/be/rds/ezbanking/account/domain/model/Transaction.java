package be.rds.ezbanking.account.domain.model;

public interface Transaction {
	
	default boolean isDeposit(){
		return getTransactionType().equals(TransactionType.DEPOSIT);
	}
	
	default boolean isTransfer(){
		return getTransactionType().equals(TransactionType.TRANSFER);
	}
	
	public TransactionType getTransactionType();
	
	public Double getAmountForAccount(Account acc);
}
