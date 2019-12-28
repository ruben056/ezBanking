package be.rds.ezbanking;

import be.rds.ezbanking.application.security.ApplicationUser;
import be.rds.ezbanking.application.security.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;

import be.rds.ezbanking.domain.AccountRepository;
import be.rds.ezbanking.domain.AccountService;
import be.rds.ezbanking.domain.model.Account;
import be.rds.ezbanking.infrastructure.CustomSequences;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EzBankApplication implements CommandLineRunner{

	@Autowired
	AccountRepository accountRepo;
	@Autowired
	AccountService accountService;
	@Autowired
	ApplicationUserRepository applicationUserRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	MongoOperations mongo;
	
	public static void main(String[] args) {
		SpringApplication.run(EzBankApplication.class, args);
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		
		cleanupDb();
		
		accountRepo.save(new Account("accountHolder1"));
		accountRepo.save(new Account("accountHolder2"));
		System.out.println(accountRepo.count());
		
		Account account1 = accountRepo.findByAccountHolder("accountHolder1");
		Account account2 = accountRepo.findByAccountHolder("accountHolder2");
		
		account2 = accountService.makeDeposit(500.0d, account2);
		System.out.println("acc2 : " + account2.getId() + ":" +  account2.getBalance());
		
		accountService.transferMoney(200.0d, account2, account1);
		
		System.out.println("acc1 : " + account1.getId() + ":" +  account1.getBalance());
		System.out.println("acc2 : " + account2.getId() + ":" +  account2.getBalance());
		
		cleanupDb();
		
		System.out.println(accountRepo.count());

		if(!applicationUserRepository.existsByUsername("user1")){
			applicationUserRepository.insert(new ApplicationUser("user1", bCryptPasswordEncoder.encode("pwd")));
		}
	}

	private void cleanupDb() {
		accountRepo.deleteAll();
		mongo.dropCollection(CustomSequences.class);
	}
}
