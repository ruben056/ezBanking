package be.rds.ezbanking.security.domain.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {

	
	ApplicationUser findByUsername(String username);
	
	boolean existsByUsername(String username);
}
