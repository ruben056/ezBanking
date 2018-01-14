package be.rds.ezbanking.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import be.rds.ezbanking.domain.model.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest{

	@Autowired
	private AccountService accountService;
	
	@Test
	public void whenMakingDepositMoneyShouldBeOnAccount() {
		Account account = createAccount("holder1", "id1");
		double amount2Deposit = 200.0d;
		accountService.makeDeposit(amount2Deposit, account);
		
		assertThat(account.getBalance()).isEqualTo(amount2Deposit);
	}
	
	@Test
	public void whenTransferingMoneyOneAccountIsCreditedAndOnIsDebeted() {
		Account debetAccount = createAccount("holder1", "id1");
		Account creditAccount = createAccount("holder2", "id2");
		double amount2Transfer = 200.0d;
		accountService.transferMoney(amount2Transfer, creditAccount, debetAccount);
		
		assertThat(creditAccount.getBalance()).isEqualTo(-amount2Transfer);
		assertThat(debetAccount.getBalance()).isEqualTo(amount2Transfer);
	}

	
	private Account createAccount(String accountHolder, String id) {
		Account account = new Account(accountHolder);
		Field field = ReflectionUtils.findField(account.getClass(), "id");
		field.setAccessible(true);
		ReflectionUtils.setField(field,  account, id);
		return account;
	} 
}
