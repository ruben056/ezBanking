package be.rds.ezbanking.domain.model;

public class Transfer implements Transaction{

//	@Id
//	private String id;
	private Double amount;
	private String creditAccountId;
	private String debetAccountId;
	private int seqNr;
	
	public Transfer() {
	}

	public Transfer(Double amount, String creditAccountId, String debetAccountId, int seqNr) {
		this.amount = amount;
		this.creditAccountId = creditAccountId;
		this.debetAccountId = debetAccountId;
		this.seqNr = seqNr;
	}

//	public String getId() {
//		return id;
//	}

	public Double getAmount() {
		return amount;
	}

	public String getCreditAccountId() {
		return creditAccountId;
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
		return TransactionType.TRANSFER;
	}

	@Override
	public Double getAmountForAccount(Account acc) {
		if(acc.getId().equals(creditAccountId)) {
			return -amount;
		}
		else {
			return amount;
		}
	}
}
