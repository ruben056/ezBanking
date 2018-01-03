package be.rds.ezbanking.domain.model;

public class Deposit  implements Transaction{

	private Double amount;
	private String debetAccountId;
	private int seqNr;
	
	public Deposit() {
	}

	public Deposit(Double amount, String debetAccountId, int seqNr) {
		this.amount = amount;
		this.debetAccountId = debetAccountId;
		this.seqNr = seqNr;
	}

	public Double getAmountForAccount(Account acc) {
		if(!this.debetAccountId.equals(acc.getId())) {
			return 0.0d;
		}
		return amount;
	}
	
	public String getDebetAccountId() {
		return debetAccountId;
	}
	
	public int getSeqNr() {
		return seqNr;
	}
	
	public void setSeqNr(int seqNr) {
		this.seqNr = seqNr;
	}
	
	public TransactionType getTransactionType(){
		return TransactionType.DEPOSIT;
	}
}
