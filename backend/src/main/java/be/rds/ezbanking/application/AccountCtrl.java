package be.rds.ezbanking.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.rds.ezbanking.application.dto.DepositMoneyCmd;
import be.rds.ezbanking.application.dto.TransferMoneyCmd;
import be.rds.ezbanking.domain.AccountRepository;
import be.rds.ezbanking.domain.AccountService;
import be.rds.ezbanking.domain.model.Account;

@RestController
@RequestMapping(path="/api/account")
public class AccountCtrl {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@RequestMapping(path="/", method = RequestMethod.GET)
	public List<Account> get() {
		return accountRepo.findAll();
	}
	
	@RequestMapping(path="/new/{accountHolder}", method = RequestMethod.POST)
	public Account create(@PathVariable("accountHolder") String accountHolder) {
		return accountRepo.save(new Account(accountHolder));
	}
	
	@RequestMapping(path="/{id}", method = RequestMethod.GET)
	public Account get(@PathVariable("id") String accountId) {
		return accountRepo.findOne(accountId);
	}
	
	@RequestMapping(path="/{id}/balance", method = RequestMethod.GET)
	public double getBalance(@PathVariable("id") String accountId) {
		return accountRepo.findOne(accountId).getBalance();
	}
	
	@RequestMapping(path="/transaction/deposit", method = RequestMethod.POST)
	public Account create(@RequestBody DepositMoneyCmd depositCmd) {
		Account account = accountRepo.findOne(depositCmd.getAccountId());
		account = accountService.makeDeposit(depositCmd.getAmount(), account);
		return accountRepo.save(account);
	}
	
	@RequestMapping(path="/transaction/transfer", method = RequestMethod.POST)
	public Iterable<Account> create(@RequestBody TransferMoneyCmd transferMoneyCmd) {
		Account creditAccount = accountRepo.findOne(transferMoneyCmd.getCreditAccountId());
		Account debetAccount = accountRepo.findOne(transferMoneyCmd.getDebetAccountId());
		Iterable<Account> accountsInvolved = accountService.transferMoney(transferMoneyCmd.getAmount(), creditAccount, debetAccount);
		return accountRepo.save(accountsInvolved);
	}
}
