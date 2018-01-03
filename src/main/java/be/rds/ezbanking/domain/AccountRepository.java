package be.rds.ezbanking.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import be.rds.ezbanking.domain.model.Account;

public interface AccountRepository extends MongoRepository<Account, String>{

	Account findByAccountHolder(String accountHolder);
}
