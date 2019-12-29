package be.rds.ezbanking.account.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import be.rds.ezbanking.account.domain.model.Account;

public interface AccountRepository extends MongoRepository<Account, String>{

	Account findByAccountHolder(String accountHolder);
}
