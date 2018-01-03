package be.rds.ezbanking.application.dto;

public class DepositMoneyCmd {

	private double amount;
	private String accountId;
	
	public DepositMoneyCmd() {};
	
	public DepositMoneyCmd(double amount, String accountId) {
		this.amount = amount;
		this.accountId = accountId;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
