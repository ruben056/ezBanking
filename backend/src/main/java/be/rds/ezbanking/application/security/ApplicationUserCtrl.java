package be.rds.ezbanking.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.rds.ezbanking.application.exceptions.UserExistsException;

@RestController
@RequestMapping(path="/api/appuser")
public class ApplicationUserCtrl {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@ExceptionHandler(UserExistsException.class)
	@ResponseStatus(org.springframework.http.HttpStatus.CONFLICT)
	public String handleAppException(UserExistsException ex) {
	  return ex.getMessage();
	}
	
	@PostMapping("/signup")
	public void signup(@RequestBody ApplicationUser user) throws UserExistsException{
		if(userExistsAllready(user)) {
			throw new UserExistsException("User " + user.getUsername() +" allready exists.");
		}else {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			applicationUserRepository.save(user);
		}
	}

	private boolean userExistsAllready(ApplicationUser user) {
		return applicationUserRepository.existsByUsername(user.getUsername());
	}
}
