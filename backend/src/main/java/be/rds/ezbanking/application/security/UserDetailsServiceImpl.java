package be.rds.ezbanking.application.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser appUser = applicationUserRepository.findByUsername(username);
		if(appUser == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return new User(appUser.getUsername(), appUser.getPassword(), new ArrayList<>());
	}

}
