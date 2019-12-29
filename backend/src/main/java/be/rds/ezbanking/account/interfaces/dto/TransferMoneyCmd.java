package be.rds.ezbanking.account.interfaces.dto;

public class TransferMoneyCmd {

	private double amount;
	private String debetAccountId;
	private String creditAccountId;
	
	public TransferMoneyCmd() {}
	
	public TransferMoneyCmd(double amount, String debetAccountId, String creditAccountId) {
		this.amount = amount;
		this.debetAccountId = debetAccountId;
		this.creditAccountId = creditAccountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDebetAccountId() {
		return debetAccountId;
	}

	public void setDebetAccountId(String debetAccountId) {
		this.debetAccountId = debetAccountId;
	}

	public String getCreditAccountId() {
		return creditAccountId;
	}

	public void setCreditAccountId(String creditAccountId) {
		this.creditAccountId = creditAccountId;
	}
	
	
	
	
}

