package be.rds.ezbanking.application.security;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {

	
	ApplicationUser findByUsername(String username);
	
	boolean existsByUsername(String username);
}
